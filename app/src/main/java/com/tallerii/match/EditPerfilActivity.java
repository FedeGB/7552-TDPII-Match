package com.tallerii.match;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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
        if(isTabletDevice) {
            FragmentEditPerfilInterestDetails fid = (FragmentEditPerfilInterestDetails) getSupportFragmentManager().findFragmentById(R.id.fragment7);
            FragmentEditperfilInterest fi = (FragmentEditperfilInterest) getSupportFragmentManager().findFragmentById(R.id.fragment6);

            fi.setFragmentEditPerfilInterestDetails(fid);
        } else {
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
