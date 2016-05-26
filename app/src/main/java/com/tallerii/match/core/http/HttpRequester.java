package com.tallerii.match.core.http;

import com.tallerii.match.core.SystemData;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public abstract class HttpRequester implements HttpResponseListener {
    @Override
    public void handleHttpResponse(String response, HttpConnection connection) {
        try {
            this.checkAndExtractPayload(new JSONObject(response));
        } catch (JSONException e) {
            endWithError("CreatingJsonObject: " + e.getMessage());
        }
    }

    @Override
    public void httpRequestError(HttpConnection connection) {
        endWithError("TimeOut");
    }

    public abstract void afterError();

    private void checkAndExtractPayload(JSONObject jsonObject){
        try {
            JSONObject response = jsonObject.getJSONObject("payload");
            this.responseArrival(response);
        } catch (JSONException e) {
            endWithError("GettingPayload: " + e.getMessage());
        }
    }

    protected void endWithError(String errorMessage){
        System.out.println(errorMessage);
        afterError();
    }

    protected abstract void responseArrival(JSONObject jsonObject);

    //TODO: Ver si esta conectado a internet realmente!
    public boolean hasValidConnection(){
        return true;
    }
}
