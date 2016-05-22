package com.tallerii.match.core.http;

import android.util.Pair;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.util.Iterator;

/**
 * Created by Demian on 22/05/2016.
 */
public class HttpPostConnection extends HttpConnection {
    private JSONObject body = new JSONObject();

    public HttpPostConnection(HttpResponseListener listener) {
        super(listener);
        this.addHeader("Content-Type", "application/json");
    }

    @Override
    public HttpURLConnection buildRequestStructure(String baseURL) {
        try {
            HttpURLConnection httpURLConnection = createConnection(baseURL);
            httpURLConnection.setRequestMethod("POST");

            JSONObject variable = new JSONObject();

            Iterator<Pair<String, String>> variableITerator = requestVariables.iterator();
            while (variableITerator.hasNext()) {
                Pair<String, String> vInfo = variableITerator.next();
                variable.put(vInfo.first, vInfo.second);
            }

            body = variable;

            byte[] array = body.toString().getBytes();
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestProperty("Content-Length", Integer.toString(array.length));
            httpURLConnection.getOutputStream().write(array);
            httpURLConnection.getOutputStream().close();

            return httpURLConnection;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setBody(JSONObject body){
        this.body = body;
    }
}
