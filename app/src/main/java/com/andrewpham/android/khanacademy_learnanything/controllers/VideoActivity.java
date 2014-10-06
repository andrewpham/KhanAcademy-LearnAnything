package com.andrewpham.android.khanacademy_learnanything.controllers;

/*
 * Copyright 2012 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.app.ActionBar;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;

import com.andrewpham.android.khanacademy_learnanything.DeveloperKey;
import com.andrewpham.android.khanacademy_learnanything.R;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class VideoActivity extends YouTubeFailureRecoveryActivity {

    private ActionBar mActionBar;

    private YouTubePlayerView mPlayerView;
    private String mYoutubeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mYoutubeId = getIntent().getStringExtra(TopicFragment.EXTRA_ID);

        super.onCreate(savedInstanceState);
        mActionBar = getActionBar();
        mActionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.actionbar_background)));
        mActionBar.setTitle(getIntent().getStringExtra(TopicFragment.EXTRA_TITLE));
        if (NavUtils.getParentActivityName(this) != null) {
            mActionBar.setDisplayHomeAsUpEnabled(true);
        }
        setContentView(R.layout.activity_video);
        mPlayerView = (YouTubePlayerView) findViewById(R.id.player);

        mPlayerView.initialize(DeveloperKey.DEVELOPER_KEY, this);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player,
                                        boolean wasRestored) {
        player.cueVideo(mYoutubeId);
    }

    @Override
    protected YouTubePlayer.Provider getYouTubePlayerProvider() {
        return mPlayerView;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (NavUtils.getParentActivityName(this) != null) {
                    NavUtils.navigateUpFromSameTask(this);
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}