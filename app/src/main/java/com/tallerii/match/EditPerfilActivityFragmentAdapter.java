package com.tallerii.match;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Demian on 25/05/2016.
 */
public class EditPerfilActivityFragmentAdapter extends FragmentPagerAdapter {
    public EditPerfilActivityFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 1:
                return new FragmentEditperfilInterest();
            case 0:
                return new FragmentEditPerfilGeneral();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
