package com.tallerii.match.core.query.http;

import com.tallerii.match.core.query.http.connections.HttpResponseListener;
import com.tallerii.match.core.query.http.connections.HttpPostConnection;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Demian on 22/05/2016.
 */
public class HttpRegisterRequest implements HttpResponseListener {

    RequesterListener requesterListener;

    public void sendRegisterRequest(String user, String name, int age, String sex,String password, RequesterListener requesterListener){
        HttpPostConnection httpConnection = new HttpPostConnection(this, "HttpRegisterRequest");
        this.requesterListener = requesterListener;

        JSONObject jsonObject = new JSONObject();

        httpConnection.setUri("users");

        try {
            jsonObject.put("username", user);
            jsonObject.put("name", name);
            jsonObject.put("password", password);
            jsonObject.put("age", age);
            jsonObject.put("sex", sex);

            httpConnection.setBody(jsonObject);
        } catch (JSONException e) {

        }

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
