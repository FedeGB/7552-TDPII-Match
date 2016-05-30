package com.tallerii.match;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.tallerii.match.core.ServerData;
import com.tallerii.match.core.query.QueryListener;
import com.tallerii.match.core.SystemData;

public class RegisterActivity extends AppCompatActivity implements QueryListener {


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

        String serverIp = ((EditText)findViewById(R.id.cr_et_ip)).getText().toString();

        String[] splittedIp = serverIp.split(":");

        SystemData.getInstance().setIp(splittedIp[0]);
        SystemData.getInstance().setPort(splittedIp[1]);
        ServerData.getInstance().registerUser(email, name, password, this);
    }

    @Override
    public void proccesRequest(Object returnedObject, String request) {
        boolean succesLoged = (boolean) returnedObject;

        if(succesLoged){
            finish();
        }
    }
}
