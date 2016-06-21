package com.tallerii.match.core.query.http;
import android.util.Base64;

import com.tallerii.match.core.query.http.connections.HttpGetConnection;
import com.tallerii.match.core.query.http.connections.HttpResponseListener;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Demian on 08/05/2016.
 */
public class HttpLoginRequester implements HttpResponseListener {

    RequesterListener requesterListener;

    public HttpLoginRequester(){

    }

    public void sendLoginRequest(String username, String password, RequesterListener requesterListener){
        this.requesterListener = requesterListener;
        HttpGetConnection httpConnection = new HttpGetConnection(this, "HttpLoginRequester");

        httpConnection.setUri("users/login");

        String userPass = username + ":" + password;
        String encodedLog = "Basic " + Base64.encodeToString(userPass.getBytes(), Base64.DEFAULT);
        httpConnection.addHeader("Authorization", encodedLog);

        httpConnection.execute();
    }

    @Override
    public void handleHttpResponse(JSONObject responseBody) {
        try {
            String token = responseBody.getString("token");
            System.out.println("http" + token);
            requesterListener.onSuccesRequest(token);
        }  catch (JSONException e) {
            handleHttpError(-2, "Error parsing user on \"handleHttpResponse\" on HttpLoginRequest.java");
        }
    }

    @Override
    public void handleHttpError(int errorNumber, String errorMessage) {
        requesterListener.onFailRequest(errorNumber, errorMessage);
    }
}
