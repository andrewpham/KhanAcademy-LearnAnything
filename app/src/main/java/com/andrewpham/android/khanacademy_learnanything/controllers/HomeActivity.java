package com.andrewpham.android.khanacademy_learnanything.controllers;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import com.andrewpham.android.khanacademy_learnanything.R;
import com.andrewpham.android.khanacademy_learnanything.adapters.NavDrawerListAdapter;
import com.andrewpham.android.khanacademy_learnanything.api.ApiClient;
import com.andrewpham.android.khanacademy_learnanything.drawer_model.NavDrawerItem;
import com.andrewpham.android.khanacademy_learnanything.oauth.OAuthClient;
import com.andrewpham.android.khanacademy_learnanything.topic_model.Child;
import com.andrewpham.android.khanacademy_learnanything.topic_model.TopicData;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class HomeActivity extends Activity {

    GridView mGridView;

    public static final String EXTRA_TRANSLATED_TITLES = "com.andrewpham.android.khanacademy_learnanything.translated_titles";
    public static final String EXTRA_NODE_SLUGS = "com.andrewpham.android.khanacademy_learnanything.node_slugs";

    private ActionBar mActionBar;

    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    private CharSequence mDrawerTitle;

    private CharSequence mTitle;

    private String[] mTopics;
    private ArrayList<String> mItems;
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
            "partner-content",
            "college-admissions",
            "talks-and-interviews",
            "coach-res"
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
        mItems = new ArrayList<>(Arrays.asList(mTopics));
        mIcons = getResources().obtainTypedArray(R.array.home_nav_drawer_icons);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mDrawerList = (ListView) findViewById(R.id.drawerListView);
        mDrawerList.setOnItemClickListener(new SlidingMenuClickListener());

        mNavDrawerItems = new ArrayList<>();

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

        mGridView = (GridView) findViewById(R.id.gridView);
        mGridView.setAdapter(new GridAdapter(mItems));
    }

    /**
     * Sliding menu item click listener
     */
    private class SlidingMenuClickListener implements
            ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            getTopic(position);
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
//            case R.id.action_login:
//                try {
//                    Intent i = new Intent(mContext, WebpageActivity.class);
//                    i.setData(Uri.parse(OAuthClient.initialize()));
//                    startActivity(i);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                return true;
            case R.id.action_folder:
                Intent j = new Intent(Intent.ACTION_VIEW);
                Uri uri = Uri.parse(Environment.getExternalStorageDirectory()
                        + "/Android/data/"
                        + this.getPackageName()
                        + "/files");
                j.setDataAndType(uri, "resource/folder");

                startActivity(Intent.createChooser(j, getString(R.string.prompt_folder)));
                return true;
            case R.id.action_email:
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setData(Uri.parse("mailto:"));
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_EMAIL, getString(R.string.app_email));

                startActivity(Intent.createChooser(i, getString(R.string.prompt_email)));
                return true;
            case R.id.action_redirect:
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.app_store_url) + getString(R.string.external_app_name))));
                } catch (android.content.ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.http_url) + getString(R.string.external_app_name))));
                }
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
        menu.findItem(R.id.action_email).setVisible(!drawerOpen);
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

    private class GridAdapter extends ArrayAdapter<String> {
        public GridAdapter(ArrayList<String> items) {
            super(getApplicationContext(), 0, items);
        }

        ViewHolder holder;

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater()
                        .inflate(R.layout.grid_item, parent, false);
                holder = new ViewHolder();
                holder.textView = (TextView) convertView.findViewById(R.id.title);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            String item = getItem(position);
            holder.textView.setText(item);

            convertView.setOnTouchListener(new View.OnTouchListener() {
                private float startX;
                private float startY;

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            startX = event.getX();
                            startY = event.getY();
                            v.setBackgroundResource(R.drawable.list_item_shape_pressed);
                            v.setPadding(v.getPaddingLeft(), v.getPaddingTop() + 4,
                                    v.getPaddingRight(), v.getPaddingBottom() - 4);
                            break;
                        case MotionEvent.ACTION_UP:
                            float endX = event.getX();
                            float endY = event.getY();
                            v.setBackgroundResource(R.drawable.list_item_shape_normal);
                            v.setPadding(v.getPaddingLeft(), v.getPaddingTop() - 4,
                                    v.getPaddingRight(), v.getPaddingBottom() + 4);
                            if (TopicFragment.isAClick(startX, endX, startY, endY)) {
                                v.playSoundEffect(android.view.SoundEffectConstants.CLICK);
                                getTopic(position);
                            }
                            break;
                        case MotionEvent.ACTION_CANCEL:
                            v.setBackgroundResource(R.drawable.list_item_shape_normal);
                            v.setPadding(v.getPaddingLeft(), v.getPaddingTop() - 4,
                                    v.getPaddingRight(), v.getPaddingBottom() + 4);
                            break;
                    }
                    return true;
                }
            });

            return convertView;
        }
    }

    static class ViewHolder {
        TextView textView;
    }

    public void getTopic(int position) {
        mTopicSlug = TOPIC_SLUGS[position];
        final ArrayList<String> translatedTitles = new ArrayList<>();
        final ArrayList<String> nodeSlugs = new ArrayList<>();
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
                Intent i = new Intent(mContext, TopicActivity.class);
                i.putExtras(bundle);
                startActivity(i);
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

}