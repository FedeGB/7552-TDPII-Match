package com.tallerii.match.core.http;
import com.tallerii.match.core.SystemData;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Demian on 08/05/2016.
 */
public class HttpLoginRequester extends HttpRequester {
    public static String TAG = "LOGGIN";

    ResponseListener responseListener;

    public HttpLoginRequester(ResponseListener responseListener){
        this.responseListener = responseListener;
    }

    public void sendLoginRequest(String username, String password){
        HttpGetConnection httpConnection = new HttpGetConnection(this);
        if(hasValidConnection()){
            httpConnection.setUri("users/login");
            httpConnection.addVariable("user", username);
            httpConnection.addVariable("password", password);
            httpConnection.execute();
        }
    }

    @Override
    protected void responseArrival(JSONObject jsonObject) {
        try {
            String token = jsonObject.getString("token");
            responseListener.httpRequestFinish(HttpLoginRequester.TAG);
            SystemData.getInstance().setToken(token);
        }  catch (JSONException e) {
            endWithError("Parsing token: " + e.getMessage());
        }
    }
}
