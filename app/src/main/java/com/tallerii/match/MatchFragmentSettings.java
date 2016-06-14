package com.tallerii.match;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;


public class MatchFragmentSettings extends Fragment implements SeekBar.OnSeekBarChangeListener {

    TextView distanceTextView;
    TextView ageTextView;

    TextView baseAgeTextView;
    TextView rangeAgeTextView;

    int distance = 0;
    int baseAge = 0;
    int rangeAge = 0;

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

        int minAge = baseAge - rangeAge;
        int maxAge = baseAge + rangeAge;

        minAge = Math.max(minAge, 12);
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
}
