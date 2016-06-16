package com.tallerii.match;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.tallerii.match.core.NullQueryListener;
import com.tallerii.match.core.ServerData;
import com.tallerii.match.core.SystemData;
import com.tallerii.match.core.UserProfile;

public class MatchActivity extends AppCompatActivity {
    private ViewPager mViewPager = null;
    private TabLayout mTabLayour = null;
    boolean isTabletDevice = false;

    private void checkIfIsTabletDevice(){
        mViewPager = (ViewPager) findViewById(R.id.container);
        mTabLayour = (TabLayout) findViewById(R.id.am_tl_tab);
        isTabletDevice = (mViewPager == null);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);

        checkIfIsTabletDevice();

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
            mTabLayour.setupWithViewPager(mViewPager);
        }

        String user = SystemData.getInstance().getUserId();
        ServerData.getInstance().fetchUserProfile(user, new NullQueryListener());
        UserProfile userProfile = new UserProfile(user);
        SystemData.getInstance().getUserManager().addToProfileList(user, userProfile);
    }

    public void onGoToPerfilMenu(View view){
        startActivity(new Intent(this, EditPerfilActivity.class));
    }
}
