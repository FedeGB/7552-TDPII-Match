package com.tallerii.match.core.http;

import com.tallerii.match.core.SystemData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Demian on 22/05/2016.
 */
public class HttpMatchListRequester extends HttpRequester {

    public void getMatchList(){
        HttpGetConnection httpConnection = new HttpGetConnection(this);
        if(hasValidConnection()){
            SystemData systemData = SystemData.getInstance();
            String userId = systemData.getUserId();
            String token = systemData.getToken();

            httpConnection.setUri("users/getMatches");
            httpConnection.addVariable("user", userId);
            httpConnection.addHeader("token", token);
        }
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
        } catch (JSONException e) {
            endWithError("Parsing matchesList: " + e.getMessage());
        }
    }
}
