package com.tallerii.match;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.tallerii.match.core.SystemData;

/**
 * Created by Demian on 25/05/2016.
 */
public class EditPerfilActivityFragmentAdapter extends FragmentPagerAdapter {

    String titles[] = {"General", "Intereses"};

    FragmentEditPerfilGeneral fragmentEditPerfilGeneral = null;

    public EditPerfilActivityFragmentAdapter(FragmentManager fm) {
        super(fm);
    }


    public FragmentEditPerfilGeneral getFragmentEditPerfilGeneral() {
        return fragmentEditPerfilGeneral;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 1:
                return new FragmentEditperfilInterest();
            case 0: {
                fragmentEditPerfilGeneral = new FragmentEditPerfilGeneral();
                return fragmentEditPerfilGeneral;
            }

        }
        return null;
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}

