package com.tallerii.match.core.http;

import com.tallerii.match.core.RequesterListener;
import com.tallerii.match.core.SystemData;
import com.tallerii.match.core.UserProfile;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HttpUserProfileRequester extends HttpRequester {
    public static String TAG = "USER_PROFILE";
    private RequesterListener requesterListener;

    public HttpUserProfileRequester(){

    }

    public void getSerializedUserProfile(String userId, RequesterListener requesterListener){
        this.requesterListener = requesterListener;
        HttpGetConnection httpConnection = new HttpGetConnection(this);
        if(hasValidConnection()){
            httpConnection.addHeader("token", SystemData.getInstance().getToken());
            httpConnection.setUri("users");
            httpConnection.addVariable("username", userId);
            httpConnection.execute();
        }
    }

    @Override
    public void afterError() {
        requesterListener.proccesRequest(null, HttpUserProfileRequester.TAG);
    }

    @Override
    protected void responseArrival(JSONObject jsonObject) {
        try {
            UserProfile userProfile = new UserProfile(jsonObject.getString("id"));
            userProfile.setName(jsonObject.getString("name"));
            userProfile.setAlias(jsonObject.getString("alias"));
            userProfile.setSex(jsonObject.getString("sex"));
            userProfile.setMail(jsonObject.getString("email"));
            userProfile.setPhoto(jsonObject.getString("photo_profile"));

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

            requesterListener.proccesRequest(userProfile, HttpUserProfileRequester.TAG);
        }  catch (JSONException e) {
            endWithError("Parsing userProfile: " + e.getMessage());
        }
    }
}
