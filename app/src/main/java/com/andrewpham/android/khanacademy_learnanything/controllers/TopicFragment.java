package com.andrewpham.android.khanacademy_learnanything.controllers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NavUtils;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.andrewpham.android.khanacademy_learnanything.R;
import com.andrewpham.android.khanacademy_learnanything.RoundedTransformation;
import com.andrewpham.android.khanacademy_learnanything.api.ApiClient;
import com.andrewpham.android.khanacademy_learnanything.topic_model.Child;
import com.andrewpham.android.khanacademy_learnanything.topic_model.TopicData;
import com.andrewpham.android.khanacademy_learnanything.video_model.VideoData;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by andrewpham on 8/26/14.
 */
public class TopicFragment extends Fragment {

    public static final String TAG = "TopicFragment";
    public static final String EXTRA_NODE_SLUG =
            "com.andrewpham.android.khanacademy_learnanything.controllers.node_slug";
    public static final String EXTRA_TITLE =
            "com.andrewpham.android.khanacademy_learnanything.controllers.title";
    public static final String EXTRA_ID =
            "com.andrewpham.android.khanacademy_learnanything.controllers.id";

    private static final String NODE_SLUG_TAG = "NodeSlugId";
    private String mNodeSlug;
    private ArrayList<String> mNodeSlugs = new ArrayList<String>();
    private HashMap<String, String> mKinds = new HashMap<String, String>();
    private HashMap<String, String> mTitles = new HashMap<String, String>();
    private HashMap<String, String> mDescriptions = new HashMap<String, String>();
    private HashMap<String, String> mDurations = new HashMap<String, String>();
    private HashMap<String, Date> mDatesAdded = new HashMap<String, Date>();
    private HashMap<String, String> mIds = new HashMap<String, String>();
    private HashMap<String, String> mImageUrls = new HashMap<String, String>();

    TopicItemAdapter mTopicItemAdapter;
    VideoItemAdapter mVideoItemAdapter;
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

        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_topic, container, false);

        if (NavUtils.getParentActivityName(getActivity()) != null) {
            getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
        }

        mListView = (ListView) v.findViewById(R.id.listView);
        LayoutInflater layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout headerLayout = (LinearLayout) layoutInflater.inflate(R.layout.topic_list_header, null, false);
        mTextView = (TextView) headerLayout.findViewById(R.id.textView);
        mListView.addHeaderView(headerLayout, null, false);
        ApiClient.get().getTopicData(mNodeSlug, new Callback<TopicData>() {
            @Override
            public void success(final TopicData topicData, Response response) {
                mTextView.setText(topicData.getDescription());
                for (Child child : topicData.getChildren()) {
                    final String nodeSlug = child.getNodeSlug();
                    if (nodeSlug.startsWith("v/") || nodeSlug.startsWith("e/") ||
                            nodeSlug.startsWith("a/") || nodeSlug.startsWith("p/")) continue;
                    mNodeSlugs.add(nodeSlug);
                    mKinds.put(nodeSlug, child.getKind());
                    ApiClient.get().getTopicData(nodeSlug, new Callback<TopicData>() {
                        @Override
                        public void success(final TopicData topicData, Response response) {
                            mTitles.put(nodeSlug, topicData.getTitle());
                            mDescriptions.put(nodeSlug, topicData.getDescription());
                            if (mTopicItemAdapter == null) {
                                setupAdapter();
                            } else {
                                mTopicItemAdapter.notifyDataSetChanged();
                            }
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
        ApiClient.get().getTopicVideos(mNodeSlug, new Callback<List<VideoData>>() {
            @Override
            public void success(List<VideoData> videoDataList, Response response) {
                for (VideoData videoData : videoDataList) {
                    String nodeSlug = videoData.getNodeSlug();
                    mNodeSlugs.add(nodeSlug);
                    mKinds.put(nodeSlug, videoData.getKind());
                    mTitles.put(nodeSlug, videoData.getTitle());
                    mDescriptions.put(nodeSlug, videoData.getDescription());
                    int duration = videoData.getDuration();
                    String remainder = Integer.toString(duration % 60);
                    String seconds = (Integer.parseInt(remainder) < 10) ? "0" + remainder : remainder;
                    mDurations.put(nodeSlug, Integer.toString(duration / 60) +
                            ":" + seconds);
                    try {
                        mDatesAdded.put(nodeSlug, new SimpleDateFormat("yyyy-MM-dd")
                                .parse(videoData.getDateAdded().substring(0, 10)));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    mIds.put(nodeSlug, videoData.getYoutubeId());
                    mImageUrls.put(nodeSlug, videoData.getImageUrl());
                    if (mVideoItemAdapter == null) {
                        setupAdapter();
                    } else {
                        mVideoItemAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long id) {
                if (mNodeSlugs.get(0).startsWith("v/")) {
                    String item = mNodeSlugs.get(pos - 1);
                    Intent i = new Intent(getActivity(), VideoActivity.class);
                    i.putExtra(EXTRA_ID, mIds.get(item));
                    i.putExtra(EXTRA_TITLE, mTitles.get(item));

                    startActivity(i);
                } else {
                    final String item = mNodeSlugs.get(pos - 1);
                    final ArrayList<String> nodeSlugs = new ArrayList<String>();
                    ApiClient.get().getTopicData(item, new Callback<TopicData>() {
                        @Override
                        public void success(final TopicData topicData, Response response) {
                            for (Child child : topicData.getChildren()) {
                                final String nodeSlug = child.getNodeSlug();
                                if (nodeSlug.startsWith("e/") || nodeSlug.startsWith("a/") ||
                                        nodeSlug.startsWith("p/")) continue;
                                nodeSlugs.add(nodeSlug);
                            }
                            if (!nodeSlugs.isEmpty()) {
                                Intent i = new Intent(getActivity(), SubtopicActivity.class);
                                i.putExtra(EXTRA_NODE_SLUG, item);
                                i.putExtra(EXTRA_TITLE, mTitles.get(item));

                                startActivity(i);
                            }
                        }

                        @Override
                        public void failure(RetrofitError error) {

                        }
                    });
                }
            }
        });

        return v;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (NavUtils.getParentActivityName(getActivity()) != null) {
                    NavUtils.navigateUpFromSameTask(getActivity());
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    void setupAdapter() {
        if (getActivity() == null || mListView == null) return;

        if (mNodeSlugs != null) {
            if (mNodeSlugs.get(0).startsWith("v/")) {
                mVideoItemAdapter = new VideoItemAdapter(mNodeSlugs);
                mListView.setAdapter(mVideoItemAdapter);
            } else {
                mTopicItemAdapter = new TopicItemAdapter(mNodeSlugs);
                mListView.setAdapter(mTopicItemAdapter);
            }
        } else {
            mListView.setAdapter(null);
        }
    }

    private class TopicItemAdapter extends ArrayAdapter<String> {
        public TopicItemAdapter(ArrayList<String> items) {
            super(getActivity(), 0, items);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getActivity().getLayoutInflater()
                        .inflate(R.layout.topic_list_item, parent, false);
            }

            String item = getItem(position);

            TextView title = (TextView) convertView.findViewById(R.id.title);
            title.setText(mTitles.get(item));

            TextView description = (TextView) convertView.findViewById(R.id.description);
            description.setText(mDescriptions.get(item));

            return convertView;
        }
    }

    @SuppressWarnings("deprecation")
    private class VideoItemAdapter extends ArrayAdapter<String> {
        public VideoItemAdapter(ArrayList<String> items) {
            super(getActivity(), 0, items);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getActivity().getLayoutInflater()
                        .inflate(R.layout.video_list_item, parent, false);
            }

            String item = getItem(position);

            TextView title = (TextView) convertView.findViewById(R.id.title);
            title.setText(mTitles.get(item));

            TextView description = (TextView) convertView.findViewById(R.id.description);
            description.setText(mDescriptions.get(item));

            TextView dateAdded = (TextView) convertView.findViewById(R.id.dateAdded);
            SimpleDateFormat ft = new SimpleDateFormat("MMMM d, yyyy");
            SpannableString dateAddedText = new SpannableString("Published:  " +
                    ft.format(mDatesAdded.get(item)));
            dateAddedText
                    .setSpan(new ForegroundColorSpan(R.color.description_text), 10, dateAddedText.length(), Spannable
                            .SPAN_EXCLUSIVE_EXCLUSIVE);
            dateAdded.setText(dateAddedText);

            TextView duration = (TextView) convertView.findViewById(R.id.duration);
            SpannableString durationText = new SpannableString("Duration:  " +
                    mDurations.get(item));
            durationText
                    .setSpan(new ForegroundColorSpan(R.color.description_text), 9, durationText.length(), Spannable
                            .SPAN_EXCLUSIVE_EXCLUSIVE);
            duration.setText(durationText);

            Display display = getActivity().getWindowManager().getDefaultDisplay();
            int width = display.getWidth();
            ImageView imageView = (ImageView) convertView
                    .findViewById(R.id.list_item_imageView);
            ViewGroup.LayoutParams params = imageView.getLayoutParams();
            params.height = (int) (width * 297. / 396);
            params.width = width;
            imageView.setLayoutParams(params);
            Picasso.with(getActivity())
                    .load(mImageUrls.get(item))
                    .transform(new RoundedTransformation(20, 0))
                    .fit()
                    .into(imageView);

            return convertView;
        }
    }

}
