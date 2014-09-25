package com.andrewpham.android.khanacademy_learnanything;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

/**
 * Created by Andrew on 22/09/2014.
 */
public class SubtopicActivity extends SingleFragmentActivity {

    @Override
    protected TopicFragment createFragment() {
        return TopicFragment.newInstance(getIntent().getStringExtra(TopicFragment.EXTRA_NODE_SLUG));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActionBar().setBackgroundDrawable(new ColorDrawable(getResources()
                .getColor(R.color.subtopic_actionbar_background)));
    }

}
