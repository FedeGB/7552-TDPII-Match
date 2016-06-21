package com.tallerii.match.core.query.http;

import com.tallerii.match.core.SystemData;
import com.tallerii.match.core.UserProfile;
import com.tallerii.match.core.query.http.connections.HttpGetConnection;
import com.tallerii.match.core.query.http.connections.HttpResponseListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Demian on 22/05/2016.
 */
public class HttpCandidatesListRequester implements HttpResponseListener {

    RequesterListener requesterListener;

    public void getMatchList(RequesterListener requesterListener){
        this.requesterListener = requesterListener;

        HttpGetConnection httpConnection = new HttpGetConnection(this, "HttpCandidatesListRequester");

        SystemData systemData = SystemData.getInstance();
        String userId = systemData.getUserId();
        String token = systemData.getToken();

        httpConnection.setUri("candidates/" + userId);
        httpConnection.addHeader("Token", token);

        httpConnection.execute();
    }

    @Override
    public void handleHttpResponse(JSONObject responseBody) {
        try {
            ArrayList<UserProfile> candidatesList = new ArrayList<>();
            JSONArray matchesList = responseBody.getJSONArray("candidates");

            for (int i = 0; i < matchesList.length(); i++){
                JSONObject user = matchesList.getJSONObject(i);

                UserProfile userProfile = new UserProfile(user.getString("email"));
                userProfile.setName(user.getString("name"));
                userProfile.setAlias(user.getString("alias"));
                userProfile.setSex(user.getString("sex"));
                userProfile.setMail(user.getString("email"));
                userProfile.setPhoto(user.getString("photo_profile"));
                userProfile.setAge(user.getInt("age"));

                //TODO: Distancia!
                double distance = user.getDouble("distance");
                JSONArray interestArray = user.getJSONArray("interests");

                for(int j = 0; j < interestArray.length(); j++){
                    JSONObject interest = interestArray.getJSONObject(j);
                    String category = interest.getString("category");
                    String value = interest.getString("value");

                    userProfile.addOnInterestCategory(category, value);
                }

                candidatesList.add(userProfile);
            }

            requesterListener.onSuccesRequest(candidatesList);
        } catch (JSONException e) {
            handleHttpError(-2, "Error trying to parse UserProfile in \"handleHttpResponse\" on HttpCandidatesListRequester.java \n" + e.getMessage());
        }
    }

    @Override
    public void handleHttpError(int errorNumber, String errorMessage) {
        requesterListener.onFailRequest(errorNumber, errorMessage);
    }
}
