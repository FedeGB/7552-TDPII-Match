package com.tallerii.match;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.tallerii.match.core.ServerData;
import com.tallerii.match.core.SystemData;
import com.tallerii.match.core.query.ChangeSearchSettingsQuery;
import com.tallerii.match.core.query.QueryListener;


public class MatchFragmentSettings extends Fragment implements SeekBar.OnSeekBarChangeListener, View.OnClickListener, QueryListener {

    TextView distanceTextView;
    TextView ageTextView;

    TextView baseAgeTextView;
    TextView rangeAgeTextView;

    int distance = 0;
    int baseAge = 0;
    int rangeAge = 0;

    int minAge = 0;
    int maxAge = 0;

    public MatchFragmentSettings() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_match_fragment_settings, container, false);

        SeekBar distanceBar = (SeekBar) fragmentView.findViewById(R.id.fmfs_sb_distance);
        SeekBar baseAgeBar = (SeekBar) fragmentView.findViewById(R.id.fmfs_sb_base);
        SeekBar rangeAgeBar = (SeekBar) fragmentView.findViewById(R.id.fmfs_sb_range);
        distanceBar.setOnSeekBarChangeListener(this);
        baseAgeBar.setOnSeekBarChangeListener(this);
        rangeAgeBar.setOnSeekBarChangeListener(this);

        distanceTextView = (TextView) fragmentView.findViewById(R.id.fmfs_tv_distance);
        ageTextView = (TextView) fragmentView.findViewById(R.id.fmfs_tv_age);
        baseAgeTextView = (TextView) fragmentView.findViewById(R.id.fmfs_tv_baseAge);
        rangeAgeTextView = (TextView) fragmentView.findViewById(R.id.fmfs_tv_rangeAge);

        Button searchButton = (Button) fragmentView.findViewById(R.id.fmfs_b_search);
        searchButton.setOnClickListener(this);



        return fragmentView;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        switch (seekBar.getId()) {
            case R.id.fmfs_sb_distance:
                distance = progress;
                break;
            case R.id.fmfs_sb_base:
                baseAge = progress;
                break;
            case R.id.fmfs_sb_range:
                rangeAge = progress;
                break;
        }

        distanceTextView.setText(distance + "km");

        minAge = baseAge - rangeAge;
        maxAge = baseAge + rangeAge;

        minAge = Math.max(minAge, 18);
        maxAge = Math.min(maxAge, 80);

        ageTextView.setText("Entre " + minAge + " y " + maxAge + " anios");
        baseAgeTextView.setText("Edad base: " + baseAge);
        rangeAgeTextView.setText("Rango edad: " + rangeAge);
    }


    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onClick(View v) {
        SystemData.getInstance().matchFragmentMatchResults.getNextMatch();
        //ServerData.getInstance().updateSearchSettings(distance, minAge, maxAge, this);
    }

    @Override
    public void onReturnedRequest(String request) {
        if(request.compareTo(ChangeSearchSettingsQuery.QUERY_TAG) == 0) {
            SystemData.getInstance().matchFragmentMatchResults.getNextMatch();
        }
        System.out.println("put por returned");
    }

    @Override
    public void onFailRequest(String message, String request) {

    }

    @Override
    public void afterRequest(String request) {
        if(request.compareTo(ChangeSearchSettingsQuery.QUERY_TAG) == 0) {
            SystemData.getInstance().matchFragmentMatchResults.getNextMatch();
        }
        System.out.println("put por after");
    }
}
