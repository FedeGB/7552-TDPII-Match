package com.tallerii.match.core.http;

import com.tallerii.match.core.RequesterListener;

import org.json.JSONObject;

/**
 * Created by Demian on 22/05/2016.
 */
public class HttpRegisterRequest extends HttpRequester {

    RequesterListener requesterListener;
    String id;

    public HttpRegisterRequest(){

    }

    public void sendRegisterRequest(String user, String name, String password, RequesterListener requesterListener){
        HttpPostConnection httpConnection = new HttpPostConnection(this);
        this.requesterListener = requesterListener;
        id = user;
        if(hasValidConnection()){
            httpConnection.setUri("users");

            httpConnection.addVariable("username", user);
            httpConnection.addVariable("name", name);
            httpConnection.addVariable("password", password);

            httpConnection.execute();
        }
    }

    @Override
    public void afterError() {
        requesterListener.proccesRequest(false, "LOGIN");
    }

    @Override
    protected void responseArrival(JSONObject jsonObject) {
        requesterListener.proccesRequest(true, "LOGIN");
    }
}
