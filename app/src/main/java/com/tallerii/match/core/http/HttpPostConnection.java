package com.tallerii.match.core.http;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;

/**
 * Created by Demian on 22/05/2016.
 */
public class HttpPostConnection extends HttpConnection {
    private ByteArrayOutputStream bodyStream = new ByteArrayOutputStream();

    public HttpPostConnection(HttpResponseListener listener) {
        super(listener);
    }

    @Override
    public HttpURLConnection buildRequestStructure(String baseURL) {
        try {
            HttpURLConnection httpURLConnection = createConnection(baseURL);
            httpURLConnection.setRequestMethod("POST");

            byte[] array = bodyStream.toByteArray();
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestProperty("Content-Length", Integer.toString(array.length));
            httpURLConnection.getOutputStream().write(array);
            httpURLConnection.getOutputStream().close();
            bodyStream.close();

            return httpURLConnection;
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void writeBody(byte[] bytes){
        try {
            bodyStream.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
