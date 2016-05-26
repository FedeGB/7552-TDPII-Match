package com.tallerii.match.core.http;
import com.tallerii.match.core.RequesterListener;
import com.tallerii.match.core.SystemData;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Demian on 08/05/2016.
 */
public class HttpLoginRequester extends HttpRequester {
    public static String TAG = "LOGGIN";

    RequesterListener responseListener;
    String id;

    public HttpLoginRequester(){

    }

    public void sendLoginRequest(String username, String password, RequesterListener responseListener){
        this.responseListener = responseListener;
        HttpGetConnection httpConnection = new HttpGetConnection(this);
        if(hasValidConnection()){
            httpConnection.setUri("users/login");
            httpConnection.addVariable("user", username);
            httpConnection.addVariable("password", password);
            id = username;
            httpConnection.execute();
        }
    }

    @Override
    public void afterError() {
        responseListener.proccesRequest(false, HttpLoginRequester.TAG);
    }

    @Override
    protected void responseArrival(JSONObject jsonObject) {
        try {
            String token = jsonObject.getString("token");
            responseListener.proccesRequest(true, HttpLoginRequester.TAG);
            SystemData.getInstance().setLogin(id, token);
        }  catch (JSONException e) {
            endWithError("Parsing token: " + e.getMessage());
        }
    }
}
