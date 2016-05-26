package com.tallerii.match;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.tallerii.match.core.InterestCategory;
import com.tallerii.match.core.UserProfile;

import java.util.Iterator;

public class EditPerfilActivity extends AppCompatActivity {

    private ViewPager mViewPager = null;
    boolean isTabletDevice = false;

    private void checkIfIsTabletDevice(){
        mViewPager = (ViewPager) findViewById(R.id.container);
        isTabletDevice = (mViewPager == null);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_perfil);

        checkIfIsTabletDevice();

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        if(!isTabletDevice) {
            EditPerfilActivityFragmentAdapter editPerfilActivityFragmentAdapter = new EditPerfilActivityFragmentAdapter(getSupportFragmentManager());
            mViewPager.setAdapter(editPerfilActivityFragmentAdapter);
            mViewPager.setCurrentItem(0);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == Activity.RESULT_OK){

        }
    }
}
