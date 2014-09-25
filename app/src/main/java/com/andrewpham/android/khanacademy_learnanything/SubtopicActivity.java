package com.andrewpham.android.khanacademy_learnanything;

/**
 * Created by Andrew on 22/09/2014.
 */
public class SubtopicActivity extends SingleFragmentActivity {

    @Override
    protected TopicFragment createFragment() {
        return TopicFragment.newInstance(getIntent().getStringExtra(TopicFragment.EXTRA_NODE_SLUG));
    }

}
