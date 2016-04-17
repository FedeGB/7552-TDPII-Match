package com.tallerii.match;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import android.os.Bundle;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class MainActivity extends AppCompatActivity implements HttpResponseListener {

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
            startActivity(intent);
        }

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
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
        HttpConnection httpConnection = new HttpConnection(getString(R.string.server_address), getString(R.string.server_port), this);
        if (httpConnection.isConnectionAvailable(this.getApplicationContext())) {
            EditText userloginEdit = (EditText) findViewById(R.id.user_email);
            EditText userpassEdit = (EditText) findViewById(R.id.user_pass);
            httpConnection.setMethod(HttpConnection.HttpMethod.Get);
            String user = userloginEdit != null ? userloginEdit.getText().toString() : "";
            String pass = userpassEdit != null ? userpassEdit.getText().toString() : "";
            if(!user.isEmpty() && !pass.isEmpty()) {
                httpConnection.setUri(getString(R.string.signin_uri));
                httpConnection.addHeader("Content-Type", "application/json");
                httpConnection.addUriVariable("user", user);
                httpConnection.addUriVariable("password", pass);
                httpConnection.execute();
            } else {
                Snackbar.make(view, "User or pass can't be empty", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        } else {
            Snackbar.make(view, "No network connection available.", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    }

    @Override
    public void handleHttpResponse(InputStream response, HttpConnection connection) {
        if(connection.getUri().equals(getString(R.string.signin_uri))) {
            Log.i(INFO_TAG, "Parsing login response: " + response.toString());
            BufferedReader reader = new BufferedReader(new InputStreamReader(response));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            try {
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                }
            } catch (IOException e) {
                Log.e(ERROR_TAG,"Input stream read error on login request", e);
            }
            Context context = MainActivity.this;
            SharedPreferences sharedPref = context.getSharedPreferences(
                    getString(R.string.preference_file_key), Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            // TODO: GET API CREDENTIALS FORM INPUT STREAM OR NEGATIVE RESPONSE
            String apiCred = "";
            editor.putString(getString(R.string.api_credential), apiCred);
            editor.apply();
        }
    }

    @Override
    public void httpRequestError(HttpConnection connection) {
        // TODO: Handle request fail
    }
}
