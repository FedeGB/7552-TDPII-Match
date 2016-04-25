package com.tallerii.match;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Iterator;

public class EditPerfilActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    UserProfile userProfile;
    ArrayAdapter<InterestCategory> listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_perfil);

        userProfile = new UserProfile();

        ListView interestListView = (ListView) findViewById(R.id.EP_LV_interestList);
        listAdapter = new EditPerfilInterestListAdapter(this);
        interestListView.setOnItemClickListener(this);
        interestListView.setAdapter(listAdapter);

        buildInterestList();
    }

    private void buildInterestList(){
        listAdapter.clear();

        Iterator<InterestCategory> interestCategoryIterator = userProfile.getInterestCategories().values().iterator();

        while (interestCategoryIterator.hasNext()){
            listAdapter.add(interestCategoryIterator.next());
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent i = new Intent(this, EditInterestActivity.class);

        String categoryName = view.getTag().toString();
        InterestCategory interestCategory = userProfile.getInterestCategories().get(categoryName);
        i.putExtra("interest", interestCategory);
        startActivityForResult(i, position);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == Activity.RESULT_OK){
            InterestCategory interestCategory = (InterestCategory) data.getSerializableExtra("interest");
            Iterator<String> a = interestCategory.getDetails().iterator();
            userProfile.getInterestCategories().put(interestCategory.getName(), interestCategory);
            buildInterestList();
        }
    }
}
