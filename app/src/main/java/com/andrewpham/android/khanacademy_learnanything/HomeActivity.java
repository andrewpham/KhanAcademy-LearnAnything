package com.andrewpham.android.khanacademy_learnanything;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.andrewpham.android.khanacademy_learnanything.api.ApiClient;
import com.andrewpham.android.khanacademy_learnanything.topic_model.Child;
import com.andrewpham.android.khanacademy_learnanything.topic_model.TopicData;
import com.andrewpham.android.khanacademy_learnanything.ui_model.NavDrawerItem;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class HomeActivity extends Activity {

    public static final String TAG = "HomeActivity";
    public static final String EXTRA_TRANSLATED_TITLES = "com.andrewpham.android.khanacademy_learnanything.translated_titles";
    public static final String EXTRA_NODE_SLUGS = "com.andrewpham.android.khanacademy_learnanything.node_slugs";

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

    private static final String[] TOPIC_SLUGS = new String[]{
            "math",
            "science",
            "economics-finance-domain",
            "humanities",
            "computing",
            "test-prep",
            "partner-content"
    };
    private String mTopicSlug;
    private static Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getApplicationContext();
        setContentView(R.layout.activity_home);

        mTitle = mDrawerTitle = getTitle();
        mTopics = getResources().getStringArray(R.array.home_nav_drawer_items);
        mIcons = getResources().obtainTypedArray(R.array.home_nav_drawer_icons);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mDrawerList = (ListView) findViewById(R.id.drawerListView);
        mDrawerList.setOnItemClickListener(new SlidingMenuClickListener());

        mNavDrawerItems = new ArrayList<NavDrawerItem>();

        for (int i = 0; i < mTopics.length; i++) {
            mNavDrawerItems.add(new NavDrawerItem(mTopics[i], mIcons.getResourceId(0, -1)));
        }

        // Recycle the typed array
        mIcons.recycle();

        mNavDrawerListAdapter = new NavDrawerListAdapter(getApplicationContext(),
                mNavDrawerItems);
        mDrawerList.setAdapter(mNavDrawerListAdapter);

        mActionBar = getActionBar();
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setHomeButtonEnabled(true);
        mActionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.actionbar_background)));
        mActionBar.setDisplayShowTitleEnabled(false);

        mDrawerToggle = new ActionBarDrawerToggle(this,
                mDrawerLayout,
                R.drawable.ic_drawer,
                R.string.app_name,
                R.string.app_name) {
            public void onDrawerClosed(View view) {
                mActionBar.setTitle(mTitle);
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                mActionBar.setTitle(mDrawerTitle);
                invalidateOptionsMenu();
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    /**
     * Sliding menu item click listener
     */
    private class SlidingMenuClickListener implements
            ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            mTopicSlug = TOPIC_SLUGS[position];
            new FetchItemsTask().execute();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Called when invalidateOptionsMenu() is triggered
     */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // if nav drawer is opened, hide the action items
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getActionBar().setTitle(mTitle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    private class FetchItemsTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            final ArrayList<String> translatedTitles = new ArrayList<String>();
            final ArrayList<String> nodeSlugs = new ArrayList<String>();
            ApiClient.get().getTopicData(mTopicSlug, new Callback<TopicData>() {
                @Override
                public void success(TopicData topicData, Response response) {
                    for (Child child : topicData.getChildren()) {
                        translatedTitles.add(child.getTranslatedTitle());
                        nodeSlugs.add(child.getNodeSlug());
                    }
                    Bundle bundle = new Bundle();
                    bundle.putStringArrayList(EXTRA_TRANSLATED_TITLES, translatedTitles);
                    bundle.putStringArrayList(EXTRA_NODE_SLUGS, nodeSlugs);
                    Intent i = new Intent(mContext, SubjectActivity.class);
                    i.putExtras(bundle);
                    startActivity(i);
                }

                @Override
                public void failure(RetrofitError error) {

                }
            });
            return null;
        }
    }

}