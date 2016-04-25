package com.tallerii.match;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class EditTextActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_text);

        Bundle extras = getIntent().getExtras();

        if(extras != null) {
            String title = extras.getString("Title");
            System.out.println(title);
            TextView tvTitle = (TextView) findViewById(R.id.ET_TV_Title);
            tvTitle.setText(title);
        }
    }

    public void OnAccept(View view){
        Intent returnIntent = new Intent();

        EditText etString = (EditText) findViewById(R.id.ET_ET_stringText);
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
