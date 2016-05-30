package com.tallerii.match;

import android.content.Intent;

import android.os.Bundle;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;


import com.tallerii.match.core.ServerData;
import com.tallerii.match.core.query.QueryListener;
import com.tallerii.match.core.SystemData;


public class MainActivity extends AppCompatActivity implements QueryListener {

    private static final String DEBUG_TAG = "HttpLoginRequestDebug";
    private static final String INFO_TAG = "HttpLoginRequestInfo";
    private static final String ERROR_TAG = "HttpLoginRequestError";

    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        PreferencesManager prefs = new PreferencesManager(this);
        String apiToken = prefs.getString(getString(R.string.api_credential));
        String apiUser = prefs.getString(getString(R.string.api_username));
        String apiPass = prefs.getString(getString(R.string.api_password));
        if(!apiToken.isEmpty() && !apiUser.isEmpty() && !apiPass.isEmpty()) {
            // TODO: volver hacer login y verificar credencial? O
            Intent intent = new Intent(this, MatchActivity.class);
            startActivity(intent);
        }

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            View view = findViewById(R.id.register_view);
            String message = extras.getString(getString(R.string.registered_response));
            Snackbar.make(view, message, Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
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
        Intent intentRegister = new Intent(this, RegisterActivity.class);
        startActivity(intentRegister);
    }

    public void sendLoginRequest(View view) {
        EditText userloginEdit = (EditText) findViewById(R.id.user_email);
        EditText userpassEdit = (EditText) findViewById(R.id.user_pass);

        String userName = userloginEdit.getText().toString();
        String userPassword = userpassEdit.getText().toString();
        String serverIp = ((EditText)findViewById(R.id.cm_et_ip)).getText().toString();

        String[] splittedIp = serverIp.split(":");

        SystemData.getInstance().setIp(splittedIp[0]);
        SystemData.getInstance().setPort(splittedIp[1]);

        ServerData.getInstance().loginUser(userName, userPassword, this);
    }

    @Override
    public void proccesRequest(Object returnedObject, String request) {
        boolean loged = (boolean) returnedObject;

        if(loged) {
            startActivity(new Intent(this, MatchActivity.class));
        }
    }

    public void debugMenuOnGoToMatchClick(MenuItem menuItem){
        startActivity(new Intent(this, MatchActivity.class));
    }
}
