package com.andrewpham.android.khanacademy_learnanything;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.andrewpham.android.khanacademy_learnanything.api.ApiClient;
import com.andrewpham.android.khanacademy_learnanything.topic_model.Child;
import com.andrewpham.android.khanacademy_learnanything.topic_model.TopicData;
import com.andrewpham.android.khanacademy_learnanything.video_list_model.TopicVideo;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by andrewpham on 8/26/14.
 */
public class TopicFragment extends Fragment {

    public static final String TAG = "TopicFragment";

    private static final String NODE_SLUG_TAG = "NodeSlugId";
    private String mNodeSlug;
    private ArrayList<String> mNodeSlugs;
    private ArrayList<String> mTitles;
    private ArrayList<String> mDescriptions;
    private ArrayList<String> mVideoCounts;
    ListView mListView;
    TextView mTextView;

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

        mListView = (ListView) v.findViewById(R.id.listView);
        LayoutInflater layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout headerLayout = (LinearLayout) layoutInflater.inflate(R.layout.subtopic_list_header, null, false);
        mTextView = (TextView) headerLayout.findViewById(R.id.textView);
        mListView.addHeaderView(headerLayout, null, false);
        new FetchItemsTask().execute();

        return v;
    }

    void setupAdapter() {
        if (getActivity() == null || mListView == null) return;

        if (mNodeSlugs != null) {
            mListView.setAdapter(new ItemAdapter(mTitles));
        } else {
            mListView.setAdapter(null);
        }
    }

    private class FetchItemsTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            final ArrayList<String> nodeSlugs = new ArrayList<String>();
            final ArrayList<String> titles = new ArrayList<String>();
            final ArrayList<String> descriptions = new ArrayList<String>();
            final ArrayList<String> videoCounts = new ArrayList<String>();
            ApiClient.get().getTopicData(mNodeSlug, new Callback<TopicData>() {
                @Override
                public void success(final TopicData topicData, Response response) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mTextView.setText(topicData.getDescription());
                        }
                    });
                    for (Child child : topicData.getChildren()) {
                        final String nodeSlug = child.getNodeSlug();
                        nodeSlugs.add(nodeSlug);
                        ApiClient.get().getTopicData(nodeSlug, new Callback<TopicData>() {
                            @Override
                            public void success(TopicData topicData, Response response) {
                                titles.add(topicData.getTitle());
                                descriptions.add(topicData.getDescription());
                                final int[] videoCount = {0};
                                for (Child child : topicData.getChildren()) {
                                    ApiClient.get().getTopicVideos(child.getNodeSlug(), new Callback<List<TopicVideo>>() {
                                        @Override
                                        public void success(List<TopicVideo> topicVideos, Response response) {
                                            Log.d(TAG, Integer.toString(topicVideos.size()));
                                        }

                                        @Override
                                        public void failure(RetrofitError error) {

                                        }
                                    });
                                }
                                videoCounts.add(Integer.toString(videoCount[0]));
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        mNodeSlugs = nodeSlugs;
                                        mTitles = titles;
                                        mDescriptions = descriptions;
                                        mVideoCounts = videoCounts;
                                        setupAdapter();
                                    }
                                });
                            }

                            @Override
                            public void failure(RetrofitError error) {

                            }
                        });
                    }
                }

                @Override
                public void failure(RetrofitError error) {

                }
            });
            return null;
        }
    }

    private class ItemAdapter extends ArrayAdapter<String> {
        public ItemAdapter(ArrayList<String> items) {
            super(getActivity(), 0, items);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getActivity().getLayoutInflater()
                        .inflate(R.layout.subtopic_list_item, parent, false);
            }

            TextView title = (TextView) convertView.findViewById(R.id.title);
            title.setText(mTitles.get(position));
            TextView videoCount = (TextView) convertView.findViewById(R.id.videoCount);
            videoCount.setText(mVideoCounts.get(position));
            TextView description = (TextView) convertView.findViewById(R.id.description);
            description.setText(mDescriptions.get(position));

            return convertView;
        }
    }

}
