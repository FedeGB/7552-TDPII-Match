package com.tallerii.match;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Iterator;
import java.util.Vector;

public class EditInterestActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    InterestCategory interestCategory;
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_interest);

        Bundle extras = getIntent().getExtras();

        if(extras != null) {
            interestCategory = (InterestCategory) extras.getSerializable("interest");
        }

        TextView title = (TextView) findViewById(R.id.EIA_TV_interest);
        title.setText(interestCategory.getName());

        ListView detailsListView = (ListView) findViewById(R.id.EIA_LV_details);
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        detailsListView.setAdapter(arrayAdapter);

        Iterator<String> detailsIterator = interestCategory.getDetails().iterator();

        while (detailsIterator.hasNext()){
            arrayAdapter.add(detailsIterator.next());
        }

        detailsListView.setOnItemClickListener(this);
    }

    public void OnAddInterestDetail(View view){
        EditText newInterest = (EditText) findViewById(R.id.EIA_ET_newInterest);
        String text = newInterest.getText().toString();

        if(text.length() >= 2) {
            arrayAdapter.add(text);
            interestCategory.addDetail(text);
            newInterest.setText("");
        }
    }

    public void OnSaveAndExit(View view){
        Intent returnIntent = new Intent();
        returnIntent.putExtra("interest", interestCategory);
        setResult(Activity.RESULT_OK,returnIntent);
        finish();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        arrayAdapter.remove(interestCategory.getDetails().elementAt(position));
        interestCategory.getDetails().remove(position);
    }
}
