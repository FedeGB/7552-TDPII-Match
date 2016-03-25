package com.tallerii.match;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public final static String EXTRA_USERLOGIN = "com.tallerii.match.USERLOGIN";
    public final static String EXTRA_USERPASS = "com.tallerii.match.USERPASS";

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
        /*Intent intent = new Intent(this, ServerCommunication.class);
        EditText userloginEdit = (EditText) findViewById(R.id.user_login);
        EditText userpassEdit = (EditText) findViewById(R.id.user_pass);
        String userlogin = userloginEdit.getText().toString();
        String userpass = userpassEdit.getText().toString();
        intent.putExtra(EXTRA_USERLOGIN, userlogin);
        intent.putExtra(EXTRA_USERPASS, userpass);
        startActivity(intent);*/
    }
}
