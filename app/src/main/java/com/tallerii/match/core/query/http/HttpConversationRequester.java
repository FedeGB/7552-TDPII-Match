package com.tallerii.match.core.query.http;

import com.tallerii.match.core.SystemData;
import com.tallerii.match.core.query.http.connections.HttpGetConnection;
import com.tallerii.match.core.query.http.connections.HttpResponseListener;

import org.json.JSONObject;

/**
 * Created by dlopez on 31/05/2016.
 */
public class HttpConversationRequester implements HttpResponseListener {

    RequesterListener requesterListener;
    public void getConversationsWith(String userId, RequesterListener requesterListener){
        this.requesterListener = requesterListener;
        HttpGetConnection httpGetConnection = new HttpGetConnection(this);
        SystemData systemData = SystemData.getInstance();
        String myId = systemData.getUserId();
        String token = systemData.getToken();

        httpGetConnection.addHeader("user1", myId);
        httpGetConnection.addHeader("user2", userId);
        httpGetConnection.addHeader("token", token);

        httpGetConnection.setUri("/conversations");

        httpGetConnection.execute();
    }

    @Override
    public void handleHttpResponse(JSONObject responseBody) {

    }

    @Override
    public void handleHttpError(int errorNumber, String errorMessage) {

    }
}
