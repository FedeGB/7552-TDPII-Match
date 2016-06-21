package com.tallerii.match.core.query.http;

import com.tallerii.match.core.SystemData;
import com.tallerii.match.core.query.http.connections.HttpPostConnection;
import com.tallerii.match.core.query.http.connections.HttpResponseListener;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Demian on 22/05/2016.
 */
public class HttpLikeRequester implements HttpResponseListener {

    RequesterListener requesterListener;

    public void sendLikeToUser(String userLikedId, boolean liked, RequesterListener requesterListener){
        this.requesterListener = requesterListener;
        HttpPostConnection httpConnection = new HttpPostConnection(this, "HttpLikeRequester");

        SystemData systemData = SystemData.getInstance();
        String userSenderId = systemData.getUserId();
        String authToken = systemData.getToken();

        httpConnection.setUri("likes");

        JSONObject body = new JSONObject();

        try {
            body.put("user1", userSenderId);
            body.put("user2", userLikedId);
            body.put("like", liked);
        } catch (JSONException e) {
            this.handleHttpError(-2, "Error building json HTTPLIKEREQUSTER");
        }
        httpConnection.setBody(body);
        httpConnection.addHeader("Token", authToken);
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
