package com.tallerii.match;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;

import com.tallerii.match.core.ServerData;
import com.tallerii.match.core.query.LoginQuery;
import com.tallerii.match.core.query.QueryListener;
import com.tallerii.match.core.SystemData;
import com.tallerii.match.core.query.RegisterQuery;

public class RegisterActivity extends AppCompatActivity implements QueryListener {

    String userName;
    String userPassw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void sendSignUp(View view) {

        EditText useremailEdit = (EditText) findViewById(R.id.user_email);
        EditText userfirstEdit = (EditText) findViewById(R.id.user_firstname);
        EditText userageEdit = (EditText) findViewById(R.id.user_age);
        EditText userpassEdit = (EditText) findViewById(R.id.user_pass);
        Switch ageSwitch = (Switch) findViewById(R.id.user_sex);

        String email = useremailEdit.getText().toString();
        String name = userfirstEdit.getText().toString();
        String password = userpassEdit.getText().toString();

        String ageString = userageEdit.getText().toString();
        int age;

        if(ageString.length() > 0) {
            age = Integer.parseInt(userageEdit.getText().toString());
        } else {
            return;
        }

        String sex = "F";

        boolean sexChecked = ageSwitch.isChecked();

        if(sexChecked) {
            sex = "M";
        }

        String serverIp = ((EditText)findViewById(R.id.cr_et_ip)).getText().toString();

        String[] splittedIp = serverIp.split(":");

        if(splittedIp.length <= 1) {
            return;
        }

        userName = email;
        userPassw = password;

        SystemData.getInstance().setIp(splittedIp[0]);
        SystemData.getInstance().setPort(splittedIp[1]);
        ServerData.getInstance().registerUser(email, name, password, age, sex, this);
    }

    @Override
    public void onReturnedRequest(String request) {
        if(request.compareTo(RegisterQuery.QUERY_TAG) == 0) {
            ServerData.getInstance().loginUser(userName, userPassw, this);
        }

        if(request.compareTo(LoginQuery.QUERY_TAG) == 0) {
            startActivity(new Intent(this, MatchActivity.class));
        }
    }

    @Override
    public void onFailRequest(String message, String request) {

        Snackbar.make(this.findViewById(android.R.id.content).getRootView(), request, Snackbar.LENGTH_LONG)
                .show();
    }

    @Override
    public void afterRequest(String request) {

    }
}
