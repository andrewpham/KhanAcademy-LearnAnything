package com.andrewpham.android.khanacademy_learnanything;

import android.app.ActionBar;
import android.content.res.TypedArray;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.widget.ListView;

import com.andrewpham.android.khanacademy_learnanything.models.NavDrawerItem;

import java.util.ArrayList;

/**
 * Created by Andrew on 19/08/2014.
 */
public class HomeFragment extends Fragment {

    private ActionBar mActionBar;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    private CharSequence mDrawerTitle;

    private CharSequence mTitle;

    private String[] mTopics;
    private TypedArray mIcons;

    private ArrayList<NavDrawerItem> mNavDrawerItems;
    private NavDrawerListAdapter mNavDrawerListAdapter;
}
