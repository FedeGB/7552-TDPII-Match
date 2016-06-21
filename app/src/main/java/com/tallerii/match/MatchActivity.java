package com.tallerii.match;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
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
import com.tallerii.match.core.query.QueryListener;

public class MatchActivity extends AppCompatActivity implements LocationListener, QueryListener {
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


        try {
             /* Use the LocationManager class to obtain GPS locations */
            LocationManager mlocManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);

            mlocManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);

        } catch (SecurityException s) {
            System.out.println("Error excepcion en gps");
        }


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
        ServerData.getInstance().fetchUserProfile(user, this);
        UserProfile userProfile = new UserProfile(user);
        SystemData.getInstance().getUserManager().addToProfileList(user, userProfile);
    }

    public void onGoToPerfilMenu(View view){
        startActivity(new Intent(this, EditPerfilActivity.class));
    }

    @Override
    public void onLocationChanged(Location location) {
        SystemData.getInstance().setLongitude(location.getLongitude());
        SystemData.getInstance().setLatitude(location.getLatitude());
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onReturnedRequest(String request) {

    }

    @Override
    public void onFailRequest(String message, String request) {

    }

    @Override
    public void afterRequest(String request) {

    }
}
