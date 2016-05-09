package com.tallerii.match.core.http;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Pair;

import com.tallerii.match.core.SystemData;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
    private Vector<Pair<String, String>> uriGetVariables;
    private ByteArrayOutputStream bodyStream;
    private int response;

    public enum HttpMethod {
        Get,
        Post
    }

    public HttpConnection(HttpResponseListener listener){
        SystemData systemData = SystemData.getInstance();
        this.serverIp = systemData.getIp();
        this.serverPort = systemData.getPort();
        this.listener = listener;
        method = "GET";
        customHeaders = new Vector<>();
        uriGetVariables = new Vector<>();
        uri = "NoUri";
        bodyStream = new ByteArrayOutputStream();
        response = -1;
    }

    public void writeBody(byte[] bytes){
        try {
            bodyStream.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addHeader(String name, String content){
        Pair<String, String> header = new Pair<>(name, content);
        customHeaders.add(header);
    }

    public void addUriVariable(String variableName, String value) {
        Pair<String, String> variable = new Pair<>(variableName, value);
        uriGetVariables.add(variable);
    }

    public void setMethod(HttpMethod method){
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

            String uriVariable = uri;

            Iterator<Pair<String, String>> variableITerator = uriGetVariables.iterator();

            if(uriGetVariables.size() > 0){
                uriVariable += "?";
            }

            while (variableITerator.hasNext()){
                Pair<String, String> vInfo = variableITerator.next();
                uriVariable += vInfo.first + "=" + vInfo.second;
                if(variableITerator.hasNext()){
                    uriVariable += "&";
                }
            }

            URL PhyscUrl = new URL("http://" + serverIp + ":" + serverPort + "/" + uriVariable);
            HttpURLConnection urlConnection = (HttpURLConnection) PhyscUrl.openConnection();
            urlConnection.setRequestMethod(method);

            Iterator<Pair<String, String>> headerIterator = customHeaders.iterator();

            while (headerIterator.hasNext()){
                Pair<String, String> headerInfo = headerIterator.next();
                urlConnection.setRequestProperty (headerInfo.first, headerInfo.second);
            }
            if(method.equals("POST")) {
                urlConnection.setDoOutput(true);
                urlConnection.getOutputStream().write(bodyStream.toByteArray());
                urlConnection.connect();
            }

            //this.response = urlConnection.getResponseCode();
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
        if(result != null) {
            listener.handleHttpResponse(result, this);
        } else {
            listener.httpRequestError(this);
        }
    }

    public String getMethod() {
        return method;
    }

    public String getUri() {
        return uri;
    }

    public int getResponseCode() {
        return response;
    }
}