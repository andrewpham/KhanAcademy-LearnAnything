package com.andrewpham.android.khanacademy_learnanything.controllers;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.andrewpham.android.khanacademy_learnanything.R;
import com.andrewpham.android.khanacademy_learnanything.oauth.OAuthClient;

/**
 * Created by Andrew on 22/09/2014.
 */
public class SubtopicActivity extends SingleFragmentActivity {

    private ActionBar mActionBar;

    @Override
    protected TopicFragment createFragment() {
        return TopicFragment.newInstance(getIntent().getStringExtra(TopicFragment.EXTRA_NODE_SLUG));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActionBar = getActionBar();
        mActionBar.setBackgroundDrawable(new ColorDrawable(getResources()
                .getColor(R.color.subtopic_actionbar_background)));
        mActionBar.setDisplayShowTitleEnabled(true);
        mActionBar.setTitle(getIntent().getStringExtra(TopicFragment.EXTRA_TITLE));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_login:
                try {
                    Intent i = new Intent(getApplicationContext(), WebpageActivity.class);
                    i.setData(Uri.parse(OAuthClient.initialize()));
                    startActivity(i);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return true;
            case R.id.action_email:
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setData(Uri.parse("mailto:"));
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_EMAIL, new String[]{getString(R.string.app_email)});

                startActivity(Intent.createChooser(i, getString(R.string.prompt_email)));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
