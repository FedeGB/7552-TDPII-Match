package com.tallerii.match.core.http;

import org.json.JSONObject;

/**
 * Created by Demian on 22/05/2016.
 */
public class HttpRegisterRequest extends HttpRequester {

    public HttpRegisterRequest(String user, String name, String password){
        HttpPostConnection httpConnection = new HttpPostConnection(this);

        if(hasValidConnection()){
            httpConnection.setUri("users");
            
            httpConnection.addVariable("username", user);
            httpConnection.addVariable("name", name);
            httpConnection.addVariable("password", password);

            httpConnection.execute();
        }
    }

    @Override
    protected void responseArrival(JSONObject jsonObject) {

    }
}
