package com.andrewpham.android.khanacademy_learnanything.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.andrewpham.android.khanacademy_learnanything.TopicFragment;

import java.util.ArrayList;

/**
 * Created by andrewpham on 8/26/14.
 */
public class TabsPagerAdapter extends FragmentStatePagerAdapter {
    private static int NUM_ITEMS;
    private ArrayList<String> mNodeSlugs;

    public TabsPagerAdapter(FragmentManager fm, int numItems, ArrayList<String> nodeSlugs) {
        super(fm);
        NUM_ITEMS = numItems;
        mNodeSlugs = nodeSlugs;
    }

    @Override
    public Fragment getItem(int index) {

        return TopicFragment.newInstance(mNodeSlugs.get(index));

    }

    @Override
    public int getCount() {
        return NUM_ITEMS;
    }
}