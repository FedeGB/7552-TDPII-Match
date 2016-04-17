package com.tallerii.match;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class RegisterActivity extends AppCompatActivity implements HttpResponseListener {

    private static final String ERROR_TAG = "registerErrorTag";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void sendSignUp(View view) {
        EditText useremailEdit = (EditText) findViewById(R.id.user_email);
        EditText userfirstEdit = (EditText) findViewById(R.id.user_firstname);
        EditText userlastEdit = (EditText) findViewById(R.id.user_lastname);
        EditText userpassEdit = (EditText) findViewById(R.id.user_pass);
        String error = "";
        String email = "";
        String firstname = "";
        String lastname = "";
        String password = "";

        try {
            email = useremailEdit != null ? useremailEdit.getText().toString() : "";
            if(email.isEmpty()) {
                error = "Email ";
            }
            firstname = userfirstEdit != null ? userfirstEdit.getText().toString() : "";
            if(firstname.isEmpty()) {
                error += "First Name ";
            }
            lastname = userlastEdit != null ? userlastEdit.getText().toString() : "";
            if(lastname.isEmpty()) {
                error += "Last Name ";
            }
            password = userpassEdit != null ? userpassEdit.getText().toString() : "";
            if(password.isEmpty()) {
                error += "Password";
            }
        } catch (NullPointerException nullEx) {
            Log.e(ERROR_TAG, "Value was NULL", nullEx);
        }
        HttpConnection httpConnection = new HttpConnection(getString(R.string.server_address), getString(R.string.server_port), this);
        if (httpConnection.isConnectionAvailable(this.getApplicationContext())) {
            if(!error.isEmpty()) {
                Snackbar.make(view, error + " required", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            } else {
                JSONObject params = new JSONObject();
                try {
                    params.put("user", email);
                    params.put("name", firstname + " " + lastname);
                    params.put("password", password);
                } catch (JSONException e) {
                    Log.e(ERROR_TAG, "Unable to add parameter to json", e);
                }
                sendSignUpRequest(httpConnection, params);
            }
        } else {
            Snackbar.make(view, "No network connection available.", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    }

    public void sendSignUpRequest(HttpConnection httpConnection, JSONObject params) {
        // Set request data for register
        httpConnection.setMethod(HttpConnection.HttpMethod.Post);
        httpConnection.setUri(getString(R.string.signup_uri));
        httpConnection.addHeader("Content-Type", "application/json");
        httpConnection.writeBody(params.toString().getBytes());
        httpConnection.execute();
    }

    @Override
    public void handleHttpResponse(InputStream response, HttpConnection connection) {
        if(connection.getUri().equals(getString(R.string.signup_uri))) {
            View view = findViewById(R.id.register_view);
            String message = "";
            boolean registered = false;
            if(connection.getResponseCode() == 200) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(response));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                try {
                    while ((line = reader.readLine()) != null) {
                        stringBuilder.append(line);
                    }
                    JSONObject jsonResp = new JSONObject(stringBuilder.toString());
                    if(jsonResp.getInt("errorNum") != 0) {
                        message = jsonResp.getString("message");
                    }
                } catch (IOException e) {
                    Log.e(ERROR_TAG, "Input stream read error on register request", e);
                } catch (JSONException e) {
                    Log.e(ERROR_TAG, "Unable to handle json creation", e);
                }
                registered = true;
            } else {
                message = "Failed request with server";
            }
            if(registered) {
                Intent intent = new Intent(this, MainActivity.class);
                // TODO: Put in extra for intent message that it was registered successfully to show on MainActivity Snackbar later
                startActivity(intent);
            } else {
                Snackbar.make(view, message, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        }
    }

    @Override
    public void httpRequestError(HttpConnection connection) {
        // TODO: Handle response fail
    }
}
