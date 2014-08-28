package com.andrewpham.android.khanacademy_learnanything;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by andrewpham on 8/26/14.
 */
public class TopicFragment extends Fragment {
    private static final String NODE_SLUG_TAG = "NodeSlugId";
    private String mNodeSlug;

    public static final TopicFragment newInstance(String nodeSlug) {
        TopicFragment fragment = new TopicFragment();
        Bundle bundle = new Bundle(1);
        bundle.putString(NODE_SLUG_TAG, nodeSlug);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        mNodeSlug = getArguments().getString(NODE_SLUG_TAG);

        super.onCreate(savedInstanceState);

        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_topic, container, false);
        TextView textView = (TextView) v.findViewById(R.id.textView);
        textView.setText(mNodeSlug);

        return v;
    }
}
