package com.tallerii.match;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Demian on 24/04/2016.
 */
public class MatchActivityFragmentAdapter extends FragmentPagerAdapter {
    String titles[] = {"Chats", "Personas", "Ajustes"};

    private MatchFragmentMatchResults mfmr = new MatchFragmentMatchResults();
    private MatchFragmentChat mfc = new MatchFragmentChat();
    private MatchFragmentSettings mfs = new MatchFragmentSettings();

    public MatchActivityFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: {
                mfc.setIsPhone();
                return mfc;
            }
            case 1:
                return mfmr;
            case 2:
                return mfs;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
