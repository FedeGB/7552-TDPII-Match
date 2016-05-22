package com.tallerii.match.core.http;

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
public abstract class HttpConnection extends AsyncTask<Void, Void, InputStream> {
    private HttpResponseListener listener;

    private Vector<Pair<String, String>> customHeaders = new Vector<>();
    protected Vector<Pair<String, String>> requestVariables = new Vector<>();

    private String uri;
    protected int response = -1;

    public void setUri(String uri) {
        this.uri = uri;
    }

    public HttpConnection(HttpResponseListener listener){
        this.listener = listener;
    }

    public void addHeader(String name, String content){
        Pair<String, String> header = new Pair<>(name, content);
        customHeaders.add(header);
    }

    public void addVariable(String variableName, String value) {
        Pair<String, String> variable = new Pair<>(variableName, value);
        requestVariables.add(variable);
    }

    protected HttpURLConnection createConnection(String url){
        try {
            URL PhyscUrl = new URL(url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) PhyscUrl.openConnection();
            httpURLConnection.setReadTimeout(5000);
            httpURLConnection.setConnectTimeout(5000);

            Iterator<Pair<String, String>> headerIterator = customHeaders.iterator();

            while (headerIterator.hasNext()){
                Pair<String, String> headerInfo = headerIterator.next();
                httpURLConnection.setRequestProperty (headerInfo.first, headerInfo.second);
            }

            return httpURLConnection;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public abstract HttpURLConnection buildRequestStructure(String baseURL);

    @Override
    protected InputStream doInBackground(Void... params) {
        InputStream resultStream = null;

        try {
            SystemData systemData = SystemData.getInstance();
            String baseUrl = "http://" + systemData.getIp() + ":" + systemData.getPort() + "/" + uri;

            HttpURLConnection httpURLConnection = buildRequestStructure(baseUrl);

            httpURLConnection.connect();
            resultStream = new BufferedInputStream(httpURLConnection.getInputStream());
            httpURLConnection.disconnect();
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

    public String getUri() {
        return uri;
    }

    public int getResponseCode() {
        return response;
    }
}
