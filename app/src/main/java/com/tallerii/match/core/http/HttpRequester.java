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
    public void handleHttpResponse(InputStream response, HttpConnection connection) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(response));
        StringBuilder stringBuilder = new StringBuilder();
        String line;

        JSONObject jsonResp = null;
        try {
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            this.responseArrival(new JSONObject(stringBuilder.toString()));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void httpRequestError(HttpConnection connection) {
        System.out.println("error");
    }

    protected abstract void responseArrival(JSONObject jsonObject);

    //TODO: Ver si esta conectado a internet realmente!
    public boolean hasValidConnection(){
        return true;
    }
}
