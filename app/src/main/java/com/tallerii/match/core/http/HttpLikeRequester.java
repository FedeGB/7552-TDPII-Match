package com.tallerii.match.core.http;

import com.tallerii.match.core.SystemData;

import org.json.JSONObject;

/**
 * Created by Demian on 22/05/2016.
 */
public class HttpLikeRequester extends HttpRequester {

    public void sendLikeToUser(String userLikedId, boolean liked){
        HttpPostConnection httpConnection = new HttpPostConnection(this);
        if(hasValidConnection()) {
            SystemData systemData = SystemData.getInstance();
            String userSenderId = systemData.getUserId();
            String authToken = systemData.getToken();

            httpConnection.setUri("users");

            httpConnection.addVariable("user1", userSenderId);
            httpConnection.addVariable("user2", userLikedId);
            httpConnection.addVariable("like", Boolean.toString(liked));
            httpConnection.addHeader("token", authToken);
            httpConnection.execute();
        }
    }

    @Override
    protected void responseArrival(JSONObject jsonObject) {

    }
}
