package com.tallerii.match.core.http;

import com.tallerii.match.core.UserProfile;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HttpUserProfileRequester extends HttpRequester {
    public static String TAG = "USER_PROFILE";
    ResponseListener responseListener;

    public HttpUserProfileRequester(ResponseListener responseListener){
        this.responseListener = responseListener;
    }

    public void getSerializedUserProfile(int userId){
        HttpConnection httpConnection = new HttpConnection(this);
        if(hasValidConnection()){
            httpConnection.setMethod(HttpConnection.HttpMethod.Get);
            httpConnection.setUri("users");
            httpConnection.addUriVariable("username", Integer.toString(userId));
            httpConnection.execute();
        }
    }

    @Override
    protected void responseArrival(JSONObject jsonObject) {
        try {
            UserProfile userProfile = new UserProfile();
            userProfile.setName(jsonObject.getString("name"));
            userProfile.setAlias(jsonObject.getString("alias"));
            userProfile.setSex(jsonObject.getString("sex"));
            userProfile.setMail(jsonObject.getString("email"));

            JSONObject location = jsonObject.getJSONObject("location");
            userProfile.setLatitude(location.getInt("latitude"));
            userProfile.setLongitude(location.getInt("longitude"));

            JSONArray interestArray = jsonObject.getJSONArray("interests");
            for(int i = 0; i < interestArray.length(); i++){
                JSONObject interest = interestArray.getJSONObject(i);
                String category = interest.getString("category");
                String value = interest.getString("value");

                userProfile.addOnInterestCategory(category, value);
            }

            responseListener.httpRequestFinish(HttpUserProfileRequester.TAG);
        }  catch (JSONException e) {
            endWithError("Parsing userProfile: " + e.getMessage());
        }
    }
}
