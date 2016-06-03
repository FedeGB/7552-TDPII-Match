package com.tallerii.match.core.query.http;

import com.tallerii.match.core.SystemData;
import com.tallerii.match.core.query.http.connections.HttpGetConnection;
import com.tallerii.match.core.query.http.connections.HttpResponseListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Demian on 31/05/2016.
 */
public class HttpGetMatchesRequester implements HttpResponseListener {
    RequesterListener requesterListener;
    public void requestUserMatches(RequesterListener requesterListener){
        this.requesterListener = requesterListener;
        String myId = SystemData.getInstance().getUserId();
        String token = SystemData.getInstance().getToken();

        HttpGetConnection httpGetConnection = new HttpGetConnection(this);
        httpGetConnection.setUri("users/getMatches");
        httpGetConnection.addVariable("user", myId);
        httpGetConnection.addHeader("Authorization", token);

        httpGetConnection.execute();
    }
    @Override
    public void handleHttpResponse(JSONObject responseBody) {
        try {
            ArrayList<String> matchesList = new ArrayList<>();
            JSONArray matches = responseBody.getJSONArray("matches");

            for (int i = 0; i < matches.length(); i++){
                matchesList.add(matches.getString(i));
            }
            requesterListener.onSuccesRequest(matchesList);
        } catch (JSONException e){
            handleHttpError(-2, "Error parsin Matches in \"handleHttpResponse\" on HttpGetMatchesRequester.java");
        }
    }

    @Override
    public void handleHttpError(int errorNumber, String errorMessage) {
        requesterListener.onFailRequest(errorNumber, errorMessage);
    }
}
