package com.andrewpham.android.khanacademy_learnanything.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.andrewpham.android.khanacademy_learnanything.TopicFragment;

/**
 * Created by andrewpham on 8/26/14.
 */
public class TabsPagerAdapter extends FragmentStatePagerAdapter {
    private static int NUM_ITEMS;

    public TabsPagerAdapter(FragmentManager fm, int numItems) {
        super(fm);
        NUM_ITEMS = numItems;
    }

    @Override
    public Fragment getItem(int index) {

        return new TopicFragment();

    }

    @Override
    public int getCount() {
        return NUM_ITEMS;
    }
}