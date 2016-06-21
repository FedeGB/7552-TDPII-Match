package com.tallerii.match.core.query.http.connections;

import android.util.Pair;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.util.Iterator;

/**
 * Created by Demian on 22/05/2016.
 */
public class HttpGetConnection extends HttpConnection {

    public HttpGetConnection(HttpResponseListener listener, String calledBy) {
        super(listener, calledBy);
    }

    @Override
    public HttpURLConnection buildRequestStructure(String baseURL) {
        InputStream resultStream = null;

        try {
            String uriVariable = "";

            Iterator<Pair<String, String>> variableITerator = requestVariables.iterator();

            if (requestVariables.size() > 0) {
                uriVariable += "?";
            }
            while (variableITerator.hasNext()) {
                Pair<String, String> vInfo = variableITerator.next();
                uriVariable += vInfo.first + "=" + vInfo.second;
                if (variableITerator.hasNext()) {
                    uriVariable += "&";
                }
            }

            String finalURL = baseURL + uriVariable;


            HttpURLConnection httpURLConnection = createConnection(finalURL);
            httpURLConnection.setRequestMethod("GET");

            return httpURLConnection;

        } catch (ProtocolException e) {
            e.printStackTrace();
        }
        return null;
    }
}
