package com.andrewpham.android.khanacademy_learnanything;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
 * Created by Andrew on 22/09/2014.
 */
public class SubtopicFragment extends Fragment {

    public static final String TAG = "SubtopicFragment";

    private static final String NODE_SLUG_TAG = "NodeSlugId";
    private String mNodeSlug;
    private ArrayList<String> mNodeSlugs;
    private ArrayList<String> mTitles;
    private ArrayList<String> mDescriptions;
    ListView mListView;
    TextView mTextView;

    public static final SubtopicFragment newInstance(String nodeSlug) {
        SubtopicFragment fragment = new SubtopicFragment();
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
        LinearLayout headerLayout = (LinearLayout) layoutInflater.inflate(R.layout.topic_list_header, null, false);
        mTextView = (TextView) headerLayout.findViewById(R.id.textView);
        mListView.addHeaderView(headerLayout, null, false);
        new FetchItemsTask().execute();
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long id) {

            }
        });

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
                                for (Child child : topicData.getChildren()) {
                                    ApiClient.get().getTopicVideos(child.getNodeSlug(), new Callback<List<TopicVideo>>() {
                                        @Override
                                        public void success(List<TopicVideo> topicVideos, Response response) {

                                        }

                                        @Override
                                        public void failure(RetrofitError error) {

                                        }
                                    });
                                }
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        mNodeSlugs = nodeSlugs;
                                        mTitles = titles;
                                        mDescriptions = descriptions;
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
                        .inflate(R.layout.topic_list_item, parent, false);
            }

            TextView title = (TextView) convertView.findViewById(R.id.title);
            title.setText(mTitles.get(position));
            TextView description = (TextView) convertView.findViewById(R.id.description);
            description.setText(mDescriptions.get(position));

            return convertView;
        }
    }

}
