package com.tallerii.match.core.query.http;

import com.tallerii.match.core.SystemData;
import com.tallerii.match.core.query.http.connections.HttpPostConnection;
import com.tallerii.match.core.query.http.connections.HttpResponseListener;

import org.json.JSONObject;

/**
 * Created by Demian on 22/05/2016.
 */
public class HttpLikeRequester implements HttpResponseListener {

    RequesterListener requesterListener;

    public void sendLikeToUser(String userLikedId, boolean liked, RequesterListener requesterListener){
        this.requesterListener = requesterListener;
        HttpPostConnection httpConnection = new HttpPostConnection(this);

        SystemData systemData = SystemData.getInstance();
        String userSenderId = systemData.getUserId();
        String authToken = systemData.getToken();

        httpConnection.setUri("likes");

        httpConnection.addVariable("user1", userSenderId);
        httpConnection.addVariable("user2", userLikedId);
        //TODO: MANDAR BOOL COMO REALBOOL
        //httpConnection.addVariable("like", Boolean.toString(liked));
        httpConnection.addHeader("token", authToken);
        httpConnection.execute();

    }

    @Override
    public void handleHttpResponse(JSONObject responseBody) {
        requesterListener.onSuccesRequest(null);
    }

    @Override
    public void handleHttpError(int errorNumber, String errorMessage) {
        requesterListener.onFailRequest(errorNumber, errorMessage);
    }
}
