package com.tallerii.match.core.query.http.connections;

import android.os.AsyncTask;
import android.util.Pair;

import com.tallerii.match.core.SystemData;

import org.json.JSONException;
import org.json.JSONObject;

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

public abstract class HttpConnection extends AsyncTask<Void, Void, String> {
    private HttpResponseListener listener;

    private Vector<Pair<String, String>> customHeaders = new Vector<>();
    protected Vector<Pair<String, String>> requestVariables = new Vector<>();

    protected String calledHttp = "";

    private String uri;

    public void setUri(String uri) {
        this.uri = uri;
    }

    public HttpConnection(HttpResponseListener listener, String calledBy){
        this.listener = listener;
        this.calledHttp = calledBy;
    }

    public void addHeader(String name, String content){
        Pair<String, String> header = new Pair<>(name, content);
        customHeaders.add(header);
    }

    //TODO: CREO QUE CON EL CAMBIO A PASAR POR HEADER LOS DATOS ESTO NO VA MAS
    public void addVariable(String variableName, String value) {
        Pair<String, String> variable = new Pair<>(variableName, value);
        requestVariables.add(variable);
    }


    protected HttpURLConnection createConnection(String url){
        try {
            URL PhyscUrl = new URL(url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) PhyscUrl.openConnection();
            httpURLConnection.setReadTimeout(120000);
            httpURLConnection.setConnectTimeout(120000);

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
    protected String doInBackground(Void... params) {
        String returnedBody = null;

        try {
            SystemData systemData = SystemData.getInstance();
            String baseUrl = "http://" + systemData.getIp() + ":" + systemData.getPort() + "/" + uri;

            HttpURLConnection httpURLConnection = buildRequestStructure(baseUrl);

            if(httpURLConnection == null){
                return null;
            }

            httpURLConnection.connect();


            InputStream result = httpURLConnection.getInputStream();
            ByteArrayOutputStream b = new ByteArrayOutputStream();

            int val = result.read();
            while (val >= 0){
                byte readedByte = (byte) val;
                b.write(readedByte);
                val = result.read();
            }

            returnedBody = new String(b.toByteArray());

            httpURLConnection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return returnedBody;
    }

    @Override
    protected void onPostExecute(String result) {

        if(result == null){
            listener.handleHttpError(-1, "Client side error in \"doInBackground\" on HttpConnection.java");
            return;
        }

        try {
            JSONObject responseObject = new JSONObject(result);

            System.out.println(result);
            JSONObject payload = responseObject.getJSONObject("payload");

            listener.handleHttpResponse(payload);
        } catch (JSONException e){
            listener.handleHttpError(-2, "Error in \"OnPostExecute\" trying to handle the response error in " + calledHttp);
        }
    }
}
