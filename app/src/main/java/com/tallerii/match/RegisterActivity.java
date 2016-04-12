package com.tallerii.match;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import org.json.JSONObject;

import java.io.InputStream;

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
        try {
            String email = useremailEdit.getText().toString();
            if(email.isEmpty()) {
                error = "Email ";
            }
            String firstname = userfirstEdit.getText().toString();
            if(firstname.isEmpty()) {
                error += "First Name ";
            }
            String lastname = userlastEdit.getText().toString();
            if(lastname.isEmpty()) {
                error += "Last Name ";
            }
            String password = userpassEdit.getText().toString();
            if(password.isEmpty()) {
                error += "Password";
            }
        } catch (NullPointerException nullEx) {
            Log.e(ERROR_TAG, "Value was NULL", nullEx);
        }
        HttpConnection httpConnection = new HttpConnection("192.168.0.102", "1234", this);
        if (httpConnection.isConnectionAvailable(this.getApplicationContext())) {
            if(!error.isEmpty()) {
                Snackbar.make(view, error + " required", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            } else {
                JSONObject params = new JSONObject();
                // TODO: Set params to json
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
        httpConnection.setUri("/users/signup"); // TODO: Se puede cambiar, sacar hardcode
        // TODO: httpConnection.addHeader("UserData", userData); Poner header application/json?
        httpConnection.writeBody(params.toString().getBytes());
        httpConnection.execute();
    }

    @Override
    public void handleHttpResponse(InputStream response, HttpConnection connection) {
        // TODO: Handle response success
    }

    @Override
    public void httpRequestError(HttpConnection connection) {
        // TODO: Handle response fail
    }
}
