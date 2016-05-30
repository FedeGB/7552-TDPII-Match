package com.tallerii.match.core.query.http;

import com.tallerii.match.core.query.http.connections.HttpResponseListener;
import com.tallerii.match.core.query.http.connections.HttpPostConnection;

import org.json.JSONObject;

/**
 * Created by Demian on 22/05/2016.
 */
public class HttpRegisterRequest implements HttpResponseListener {

    RequesterListener requesterListener;

    public void sendRegisterRequest(String user, String name, String password, RequesterListener requesterListener){
        HttpPostConnection httpConnection = new HttpPostConnection(this);
        this.requesterListener = requesterListener;

        httpConnection.setUri("users");

        httpConnection.addVariable("username", user);
        httpConnection.addVariable("name", name);
        httpConnection.addVariable("password", password);

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
