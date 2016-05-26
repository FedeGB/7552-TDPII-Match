package com.tallerii.match.core.http;

import com.tallerii.match.core.RequesterListener;
import com.tallerii.match.core.SystemData;

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

            httpConnection.setUri("users/getMatches");
            httpConnection.addVariable("user", userId);
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
            ArrayList<String> userMatches = new ArrayList<>();
            JSONArray matchesList = jsonObject.getJSONArray("matches");
            for (int i = 0; i < matchesList.length(); i++){
                String match = matchesList.getString(i);
                userMatches.add(match);
            }

            requesterListener.proccesRequest(userMatches, "MATCH");
        } catch (JSONException e) {
            endWithError("Parsing matchesList: " + e.getMessage());
        }
    }
}
