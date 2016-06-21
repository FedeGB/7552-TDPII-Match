package com.tallerii.match.core.query.http.connections;

import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;

/**
 * Created by Demian on 26/05/2016.
 */
public class HttpPutConnection extends HttpConnection {
    private JSONObject body = new JSONObject();

    public HttpPutConnection(HttpResponseListener listener, String calledBy) {
        super(listener, calledBy);
    }

    public void addBody(JSONObject body){
        this.body = body;
    }

    @Override
    public HttpURLConnection buildRequestStructure(String baseURL) {
        try {
            HttpURLConnection httpURLConnection = createConnection(baseURL);
            httpURLConnection.setRequestMethod("PUT");

            byte[] array = body.toString().getBytes();
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestProperty("Content-Length", Integer.toString(array.length));
            httpURLConnection.getOutputStream().write(array);
            httpURLConnection.getOutputStream().close();

            return httpURLConnection;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
