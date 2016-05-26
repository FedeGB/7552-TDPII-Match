package com.tallerii.match;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.tallerii.match.core.http.HttpConnection;
import com.tallerii.match.core.http.HttpResponseListener;

import java.io.InputStream;

public class MatchActivity extends AppCompatActivity {
    private ViewPager mViewPager = null;
    boolean isTabletDevice = false;

    private void checkIfIsTabletDevice(){
        mViewPager = (ViewPager) findViewById(R.id.container);
        isTabletDevice = (mViewPager == null);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);

        checkIfIsTabletDevice();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            // Create the adapter that will return a fragment for each of the three
            // primary sections of the activity.
        if(isTabletDevice) {
            MatchFragmentChat matchFragmentChat = (MatchFragmentChat) getSupportFragmentManager().findFragmentById(R.id.fragment2);
            MatchFragmentConversation matchFragmentConversation = (MatchFragmentConversation) getSupportFragmentManager().findFragmentById(R.id.fragment3);
            matchFragmentChat.setMatchFragmentConversation(matchFragmentConversation);
        } else {
            MatchActivityFragmentAdapter matchActivityFragmentAdapter = new MatchActivityFragmentAdapter(getSupportFragmentManager());
            mViewPager.setAdapter(matchActivityFragmentAdapter);
            mViewPager.setCurrentItem(1);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_match, menu);
        return true;
    }

    public void onGoToPerfilMenu(MenuItem menuItem){
        startActivity(new Intent(this, EditPerfilActivity.class));
    }
}
