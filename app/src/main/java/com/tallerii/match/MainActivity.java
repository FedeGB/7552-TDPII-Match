package com.tallerii.match;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;


import java.io.InputStream;



public class MainActivity extends AppCompatActivity implements HttpResponseListener {

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

        HttpConnection httpConnection = new HttpConnection("192.168.0.102", "1234", this);

        EditText userloginEdit = (EditText) findViewById(R.id.user_login);
        EditText userpassEdit = (EditText) findViewById(R.id.user_pass);
        String userData = "{ \"User\":\"" + userloginEdit.getText().toString() + "\", \"Pass\":\"" +
                userpassEdit.getText().toString() + "\"}";

        httpConnection.setMethod(HttpConnection.HttpMethod.Post);
        httpConnection.setUri("login");
        httpConnection.addHeader("UserData", userData);

        httpConnection.writeBody("Este es el cuerpo del httpp".getBytes());
        httpConnection.addUriVariable("Nombre", "Jorge");
        httpConnection.execute();
    }

    @Override
    public void handleHttpResponse(InputStream response, HttpConnection connection) {
        if(connection.getUri() == "login"){
            //Aca llega la respuesta en un InputStream ya que no necesariamente puede ser texto!!!
            //Se puede armar un switch, o un hashmap que hashie la respuesta a otra cosa
            //Definir!
        }
    }

    @Override
    public void httpRequestError(HttpConnection connection) {

    }
}
