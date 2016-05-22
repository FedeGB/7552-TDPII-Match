package com.tallerii.match.core.http;

import com.tallerii.match.core.UserProfile;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HttpUserProfileRequester extends HttpRequester {
    public static String TAG = "USER_PROFILE";


    public HttpUserProfileRequester(){

    }

    public void getSerializedUserProfile(String userId){
        HttpGetConnection httpConnection = new HttpGetConnection(this);
        if(hasValidConnection()){
            httpConnection.setUri("users");
            httpConnection.addVariable("username", userId);
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

            //responseListener.httpRequestFinish(HttpUserProfileRequester.TAG);
        }  catch (JSONException e) {
            endWithError("Parsing userProfile: " + e.getMessage());
        }
    }
}
