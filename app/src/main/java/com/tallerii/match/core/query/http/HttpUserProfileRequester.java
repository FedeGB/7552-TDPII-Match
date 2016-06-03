package com.tallerii.match.core.query.http;

import com.tallerii.match.core.query.http.connections.HttpResponseListener;
import com.tallerii.match.core.SystemData;
import com.tallerii.match.core.UserProfile;
import com.tallerii.match.core.query.http.connections.HttpGetConnection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HttpUserProfileRequester implements HttpResponseListener {

    private RequesterListener requesterListener;

    public void getSerializedUserProfile(String userId, RequesterListener requesterListener){
        this.requesterListener = requesterListener;
        HttpGetConnection httpConnection = new HttpGetConnection(this);

        httpConnection.addHeader("token", SystemData.getInstance().getToken());
        httpConnection.setUri("users" + "/" + userId);
        httpConnection.execute();
    }

    @Override
    public void handleHttpResponse(JSONObject responseBody) {
        try {
            UserProfile userProfile = new UserProfile(responseBody.getString("id"));
            userProfile.setName(responseBody.getString("name"));
            userProfile.setAlias(responseBody.getString("alias"));
            userProfile.setSex(responseBody.getString("sex"));
            userProfile.setMail(responseBody.getString("email"));
            userProfile.setPhoto(responseBody.getString("photo_profile"));

            JSONObject location = responseBody.getJSONObject("location");
            userProfile.setLatitude(location.getInt("latitude"));
            userProfile.setLongitude(location.getInt("longitude"));

            JSONArray interestArray = responseBody.getJSONArray("interests");

            for(int i = 0; i < interestArray.length(); i++){
                JSONObject interest = interestArray.getJSONObject(i);
                String category = interest.getString("category");
                String value = interest.getString("value");

                userProfile.addOnInterestCategory(category, value);
            }

            requesterListener.onSuccesRequest(userProfile);
        }  catch (JSONException e) {
            handleHttpError(-2, "Error parsing UserProfile in \"handleHttpResponse\" on HttpUserProfileRequester.java");
        }
        System.out.println(responseBody.toString());
    }

    @Override
    public void handleHttpError(int errorNumber, String errorMessage) {
        requesterListener.onFailRequest(errorNumber, errorMessage);
    }
}
