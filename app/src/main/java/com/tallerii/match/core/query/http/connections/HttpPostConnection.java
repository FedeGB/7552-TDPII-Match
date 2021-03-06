package com.tallerii.match.core.query.http.connections;

import android.util.Pair;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Iterator;

/**
 * Created by Demian on 22/05/2016.
 */
public class HttpPostConnection extends HttpConnection {
    private JSONObject body = new JSONObject();
    boolean hasBody = false;

    public HttpPostConnection(HttpResponseListener listener, String calledBy) {
        super(listener, calledBy);
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

            if(!hasBody) {
                body = variable;
            }

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
        hasBody = true;
    }
}
