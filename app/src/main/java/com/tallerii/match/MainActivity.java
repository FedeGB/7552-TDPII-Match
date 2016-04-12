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

    //public final static String EXTRA_USERLOGIN = "com.tallerii.match.USERLOGIN";
    //public final static String EXTRA_USERPASS = "com.tallerii.match.USERPASS";
    private static final String DEBUG_TAG = "HttpLoginRequestDebug";
    private static final String INFO_TAG = "HttpLoginRequestInfo";
    private static final String ERROR_TAG = "HttpLoginRequestError";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Context context = MainActivity.this;
        SharedPreferences sharedPref = context.getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        String apiToken = sharedPref.getString(getString(R.string.api_credential), "");
        if(!apiToken.isEmpty()) {
            Intent intent = new Intent(this, MatchActivity.class);
            //EditText userloginEdit = (EditText) findViewById(R.id.user_login);
            //EditText userpassEdit = (EditText) findViewById(R.id.user_pass);
            //String userlogin = userloginEdit.getText().toString();
            //String userpass = userpassEdit.getText().toString();
            //intent.putExtra(EXTRA_USERLOGIN, userlogin);
            //intent.putExtra(EXTRA_USERPASS, userpass);
            startActivity(intent);
        }

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        //fab.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View view) {
        //        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
        //                .setAction("Action", null).show();
        //    }
        //});
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

    public void registerActivity(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    public void sendLoginRequest(View view) {
        // Gets the URL from the UI's text field.
        //String stringUrl = urlText.getText().toString();
        EditText userloginEdit = (EditText) findViewById(R.id.user_email);
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
            JSONObject jObject = null;
            String credentials = params[0] + ":" + params[1];
            String base64EncodedCredentials = Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
            String response = "";
            boolean auth;
            try {
                response = sendLoginRequest(base64EncodedCredentials); // 0: user, 1: pass
                jObject = new JSONObject(response);
                auth = jObject.getBoolean("authenticated");
            } catch (JSONException e) {
                Log.e(ERROR_TAG, "Unable to handle Json: " + response, e);
                return "";
            } catch (IOException e) {
                Log.e(ERROR_TAG, "Unable to handle login: " + base64EncodedCredentials, e);
                return "";
            }
            if(!auth) {
                return base64EncodedCredentials;
            } else {
                return "";
            }
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            if(!result.isEmpty()) {
                Context context = MainActivity.this;
                SharedPreferences sharedPref = context.getSharedPreferences(
                      getString(R.string.preference_file_key), Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString(getString(R.string.api_credential), result);
                editor.commit();
            }

        }

        private String sendLoginRequest(String base64EncodedCredentials) throws IOException {
            InputStream is = null;
            String loginReqEP = ""; //"/users/auth";
            // Log.i(INFO_TAG, "Attempting login request for " + userlogin + " with " + userpass);
            Log.i(INFO_TAG, "Attempting login request for credentials: " + base64EncodedCredentials);
            String apiAddress = getResources().getString(R.string.api_address);

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
