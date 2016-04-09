package com.tallerii.match;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    public final static String EXTRA_USERLOGIN = "com.tallerii.match.USERLOGIN";
    public final static String EXTRA_USERPASS = "com.tallerii.match.USERPASS";
    private static final String DEBUG_TAG = "HttpLoginRequestDebug";
    private static final String INFO_TAG = "HttpLoginRequestInfo";
    private static final String ERROR_TAG = "HttpLoginRequestError";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void sendLoginRequest(View view) {
        // Para llamar a otra actividad con la data entrante
        // Ver si realmente el modulo que se va a comunicar con el server
        // es un activity o que se usa
        /*Intent intent = new Intent(this, HttpActivity.class);
        EditText userloginEdit = (EditText) findViewById(R.id.user_login);
        EditText userpassEdit = (EditText) findViewById(R.id.user_pass);
        String userlogin = userloginEdit.getText().toString();
        String userpass = userpassEdit.getText().toString();
        intent.putExtra(EXTRA_USERLOGIN, userlogin);
        intent.putExtra(EXTRA_USERPASS, userpass);
        startActivity(intent);*/
        // Gets the URL from the UI's text field.
        //String stringUrl = urlText.getText().toString();
        EditText userloginEdit = (EditText) findViewById(R.id.user_login);
        EditText userpassEdit = (EditText) findViewById(R.id.user_pass);
        String userlogin;
        String userpass;
        try {
            userlogin = userloginEdit.getText().toString();
            userpass = userpassEdit.getText().toString();
        } catch(NullPointerException ex) {
            userlogin = "";
            userpass = "";
        }
        ConnectivityManager connMgr = (ConnectivityManager)
        getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            if(userlogin.isEmpty() || userpass.isEmpty()) {
                Snackbar.make(view, "Email or Password can't be empty.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            } else {
                new HttpLoginRequestTask().execute(userlogin, userpass);
            }
        } else {
            Snackbar.make(view, "No network connection available.", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    }

    private class HttpLoginRequestTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {

            try {
                return sendLoginRequest(params[0], params[1]);
            } catch (IOException e) {
                return "Unable to process login";
            }
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            JSONObject jObject = null;
            try {
                jObject = new JSONObject(result);

                if(jObject != null) {
                    boolean auth = jObject.getBoolean("autentication");
                }
            } catch (JSONException e) {
                Log.e(ERROR_TAG, "Unable to handle Json: " + result);
            }
            //Context context = MainActivity.this;
            //SharedPreferences sharedPref = context.getSharedPreferences(
              //      getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        }

        private String sendLoginRequest(String userlogin, String userpass) throws IOException {
            InputStream is = null;
            String loginReqEP = ""; //"/users/auth";
            Log.i(INFO_TAG, "Attempting login request for " + userlogin + " with " + userpass);
            String apiAddress = getResources().getString(R.string.api_address);
            String credentials = userlogin + ":" + userpass;
            String base64EncodedCredentials = Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
            String requestAddress = apiAddress + loginReqEP;
            try {
                URL url = new URL(requestAddress);
                HttpURLConnection conn = null;
                conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(10000 /* milliseconds */);
                conn.setConnectTimeout(15000 /* milliseconds */);
                conn.setRequestMethod("GET");
                conn.setDoInput(true);
                conn.setRequestProperty("Authorization", "basic " + base64EncodedCredentials);
                conn.disconnect();
                conn.connect();
                int response = conn.getResponseCode();
                if(response != 200) {
                    String error = "Failed with response: " + response
                            + " request was: " + conn.getRequestMethod();
                    Log.e(ERROR_TAG, error);
                    throw new IOException(error);
                }
                Log.d(DEBUG_TAG, "The response code is: " + response);
                is = conn.getInputStream();
                BufferedReader r = new BufferedReader(new InputStreamReader(is));
                StringBuilder total = new StringBuilder();
                String line;
                while ((line = r.readLine()) != null) {
                    total.append(line);
                }
                return total.toString();
            } catch(Exception e) {
                e.getMessage();
                return "";
            } finally {
                if (is != null) {
                    is.close();
                }
            }
        }
    }
}
