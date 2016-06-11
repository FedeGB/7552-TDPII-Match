package com.tallerii.match;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Demian on 24/04/2016.
 */
public class MatchActivityFragmentAdapter extends FragmentPagerAdapter {
    String titles[] = {"Chats", "Personas", "Ajustes"};

    public MatchActivityFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: {
                MatchFragmentChat matchFragmentChat = new MatchFragmentChat();
                matchFragmentChat.setIsPhone();
                return matchFragmentChat;
            }
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

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
