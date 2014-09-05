package com.andrewpham.android.khanacademy_learnanything;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.andrewpham.android.khanacademy_learnanything.api.ApiClient;
import com.andrewpham.android.khanacademy_learnanything.topic_model.Child;
import com.andrewpham.android.khanacademy_learnanything.topic_model.TopicData;

import java.util.ArrayList;
import java.util.Arrays;

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
    private String[] mNodeSlugs;

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
        new FetchItemsTask().execute();

        return v;
    }

    private class FetchItemsTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            final ArrayList<String> nodeSlugs = new ArrayList<String>();
            final ArrayList<String> titles = new ArrayList<String>();
            final ArrayList<String> descriptions = new ArrayList<String>();
            ApiClient.get().getTopicData(mNodeSlug, new Callback<TopicData>() {
                @Override
                public void success(TopicData topicData, Response response) {
                    for (Child child : topicData.getChildren()) {
                        String nodeSlug = child.getNodeSlug();
                        nodeSlugs.add(nodeSlug);
                        Log.d(TAG, nodeSlug);
                    }
                    mNodeSlugs = nodeSlugs.toArray(new String[nodeSlugs.size()]);
                    Log.d(TAG, Arrays.toString(mNodeSlugs));
                }

                @Override
                public void failure(RetrofitError error) {

                }
            });
            return null;
        }
    }

//
//    private class GalleryItemAdapter extends ArrayAdapter<String> {
//        public GalleryItemAdapter(ArrayList<String> items) {
//            super(getActivity(), 0, items);
//        }
//
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//            if (convertView == null) {
//                convertView = getActivity().getLayoutInflater()
//                        .inflate(R.layout.fragment_topic, parent, false);
//            }
//
//            String item = getItem(position);
//            TextView textView = (TextView) convertView.findViewById(R.id.title);
//            TextView textView2 = (TextView) convertView.findViewById(R.id.description);
//            textView.setText(titles.get(item));
//            textView2.setText(descriptions.get(item));
//
//            return convertView;
//        }
//    }

}
