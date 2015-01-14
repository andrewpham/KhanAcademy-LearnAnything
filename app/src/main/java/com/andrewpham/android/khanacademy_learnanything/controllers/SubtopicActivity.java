package com.andrewpham.android.khanacademy_learnanything.controllers;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.andrewpham.android.khanacademy_learnanything.R;

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
            case R.id.action_email:
//                try {
//                    Intent i = new Intent(getApplicationContext(), WebpageActivity.class);
//                    i.setData(Uri.parse(OAuthClient.initialize()));
//                    startActivity(i);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setData(Uri.parse("mailto:"));
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_EMAIL, new String[]{"drew.t.pham@gmail.com"});

                startActivity(Intent.createChooser(i, "Send Email"));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
