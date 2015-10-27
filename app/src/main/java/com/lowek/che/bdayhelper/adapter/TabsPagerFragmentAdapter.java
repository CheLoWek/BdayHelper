package com.lowek.che.bdayhelper.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.lowek.che.bdayhelper.fragment.ExampleFragment;
import com.lowek.che.bdayhelper.fragment.RecyclerviewFragment;

public class TabsPagerFragmentAdapter extends FragmentPagerAdapter {

    private String[] tabs;

    public TabsPagerFragmentAdapter(FragmentManager fm) {
        super(fm);
        tabs = new String[]{
                "all",
                "vk",
                "facebook",
                "gmail"
        };

    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs[position];
//        return null;
    }


    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return RecyclerviewFragment.getInstance();
            case 1:
                return ExampleFragment.getInstance();
            case 2:
                return RecyclerviewFragment.getInstance();
            case 3:
                return RecyclerviewFragment.getInstance();
            case 4:
                return RecyclerviewFragment.getInstance();
            case 5:
                return RecyclerviewFragment.getInstance();
            case 6:
                return RecyclerviewFragment.getInstance();
        }

        return null;
    }

    @Override
    public int getCount() {
        return tabs.length;
    }
}
