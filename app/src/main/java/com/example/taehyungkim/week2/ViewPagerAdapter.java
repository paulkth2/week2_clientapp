package com.example.taehyungkim.week2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter {


    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0){
            return new listFragment();
        }

        else {
            DemoFragment demoFragment = new DemoFragment();
            position = position + 1;
            Bundle bundle = new Bundle();
            bundle.putString("message", "Fragement :" + position);
            demoFragment.setArguments(bundle);

            return demoFragment;
        }
    }

    @Override
    public int getCount() {
        //# of tabs
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0){
            return "CONTACT";
        }
        else if (position==1){
            return "GALLERY";
        }
        if(position == 2) {
            return "MUSIC";
        }

        else{
            return "Fragment";
        }
    }
}


