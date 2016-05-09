package com.tallerii.match;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Demian on 24/04/2016.
 */
public class MatchActivityFragmentAdapter extends FragmentPagerAdapter {
    public MatchActivityFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new MatchFragmentChat();
            case 1:
                return new MatchFragmentMatchResults();
            case 2:
                return new MatchFragmentSettings();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}