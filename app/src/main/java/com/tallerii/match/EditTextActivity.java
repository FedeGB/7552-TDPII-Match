package com.tallerii.match;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.tallerii.match.core.SystemData;

public class EditTextActivity extends AppCompatActivity {

    int code = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_text);

        Bundle extras = getIntent().getExtras();

        if(extras != null) {
            String title = extras.getString("Title");
            code = extras.getInt("Code");
            TextView tvTitle = (TextView) findViewById(R.id.ET_TV_Title);
            tvTitle.setText(title);
        }

        if(code == 1) {
            EditText editText = (EditText) findViewById(R.id.ET_ET_stringText);
            editText.setInputType(InputType.TYPE_CLASS_NUMBER);
        }
    }

    public void OnAccept(View view){
        Intent returnIntent = new Intent();
        EditText etString = (EditText) findViewById(R.id.ET_ET_stringText);
        String write = etString.getText().toString();

        switch (code) {
            case 1: {
                String id = SystemData.getInstance().getUserId();
                int newAge = Integer.parseInt(write);
                SystemData.getInstance().getUserManager().getUserProfile(id).setAge(newAge);
                break;
            }
            case 2: {
                String id = SystemData.getInstance().getUserId();
                SystemData.getInstance().getUserManager().getUserProfile(id).setAlias(write);
                break;
            }

        }


        returnIntent.putExtra("result", etString.getText().toString());
        setResult(Activity.RESULT_OK,returnIntent);
        finish();
    }

    public void OnCancel(View view){
        Intent returnIntent = new Intent();
        setResult(Activity.RESULT_CANCELED, returnIntent);
        finish();
    }
}
