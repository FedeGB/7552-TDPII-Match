package com.tallerii.match;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.tallerii.match.core.DataFacade;
import com.tallerii.match.core.RequesterListener;
import com.tallerii.match.core.http.HttpConnection;
import com.tallerii.match.core.http.HttpResponseListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class RegisterActivity extends AppCompatActivity implements RequesterListener {


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

        String email = useremailEdit.getText().toString();
        String name = userfirstEdit.getText().toString() + " " + userlastEdit.getText().toString();
        String password = userpassEdit.getText().toString();

        DataFacade.getInstance().registerUser(email, name, password, this);

    }

    @Override
    public void proccesRequest(Object returnedObject, String request) {
        boolean succesLoged = (boolean) returnedObject;

        if(succesLoged){
            finish();
        }
    }
}
