package com.tallerii.match.core.http;

import com.tallerii.match.core.RequesterListener;
import com.tallerii.match.core.SystemData;
import com.tallerii.match.core.UserProfile;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Demian on 22/05/2016.
 */
public class HttpMatchListRequester extends HttpRequester {

    RequesterListener requesterListener;
    public void getMatchList(RequesterListener requesterListener){
        this.requesterListener = requesterListener;
        HttpGetConnection httpConnection = new HttpGetConnection(this);
        if(hasValidConnection()){
            SystemData systemData = SystemData.getInstance();
            String userId = systemData.getUserId();
            String token = systemData.getToken();

            httpConnection.setUri("candidates/" + userId);
            httpConnection.addHeader("token", token);

            httpConnection.execute();
        }
    }

    @Override
    public void afterError() {
        requesterListener.proccesRequest(new ArrayList<String>(), "MATCH");
    }

    @Override
    protected void responseArrival(JSONObject jsonObject) {
        try {
            ArrayList<UserProfile> candidatesList = new ArrayList<>();
            JSONArray matchesList = jsonObject.getJSONArray("candidates");
            for (int i = 0; i < matchesList.length(); i++){
                JSONObject user = matchesList.getJSONObject(i);
                UserProfile userProfile = new UserProfile(user.getString("id"));
                userProfile.setName(user.getString("name"));
                userProfile.setAlias(user.getString("alias"));
                userProfile.setPhoto(user.getString("photo_profile"));
                candidatesList.add(userProfile);
            }

            requesterListener.proccesRequest(candidatesList, "MATCH");
        } catch (JSONException e) {
            endWithError("Parsing matchesList: " + e.getMessage());
        }
    }
}
