package com.tallerii.match;

import android.os.AsyncTask;
import android.util.Pair;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.Vector;

/**
 * Created by Demian on 10/04/2016.
 */
public class HttpConnection extends AsyncTask<Void, Void, InputStream> {
    private String serverIp;
    private String serverPort;
    private HttpResponseListener listener;
    private String method;
    private String uri;
    private Vector<Pair<String, String>> customHeaders;
    private ByteArrayOutputStream bodyStream;

    enum HttpMethod {
        Get,
        Post
    }

    HttpConnection(String serverIp, String serverPort, HttpResponseListener listener){
        this.serverIp = serverIp;
        this.serverPort = serverPort;
        this.listener = listener;
        method = "GET";
        customHeaders = new Vector<>();
        uri = "NoUri";
        bodyStream = new ByteArrayOutputStream();
    }

    void writeBody(byte[] bytes){
        try {
            bodyStream.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void addHeader(String name, String content){
        Pair<String, String> header = new Pair<>(name, content);
        customHeaders.add(header);
    }

    void setMethod(HttpMethod method){
        switch (method) {
            case Get:
                this.method = "GET";
                break;
            case Post:
                this.method = "POST";
                break;
            default:
                break;
        }
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    @Override
    protected InputStream doInBackground(Void... params) {
        InputStream resultStream = null;

        try {
            URL PhyscUrl = new URL("http://" + serverIp + ":" + serverPort + "/" + uri);
            HttpURLConnection urlConnection = (HttpURLConnection) PhyscUrl.openConnection();
            urlConnection.setDoOutput(true);

            Iterator<Pair<String, String>> headerIterator = customHeaders.iterator();

            while (headerIterator.hasNext()){
                Pair<String, String> headerInfo = headerIterator.next();
                urlConnection.setRequestProperty (headerInfo.first, headerInfo.second);
            }

            urlConnection.getOutputStream().write(bodyStream.toByteArray());

            urlConnection.setRequestMethod(method);
            urlConnection.connect();

            resultStream = new BufferedInputStream(urlConnection.getInputStream());

            urlConnection.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultStream;
    }

    @Override
    protected void onPostExecute(InputStream result) {
        listener.handleHttpResponse(result, this);
    }

    public String getMethod() {
        return method;
    }

    public String getUri() {
        return uri;
    }
}
