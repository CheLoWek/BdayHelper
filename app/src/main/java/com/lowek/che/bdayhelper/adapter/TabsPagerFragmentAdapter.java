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
                "ALL",
                "VK",
                "FACEBOOK"
//                "tab1",
//                "tab2",
//                "tab3",
//                "tab4"
        };
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs[position];
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
        }

        return null;
    }

    @Override
    public int getCount() {
        return tabs.length;
    }
}
