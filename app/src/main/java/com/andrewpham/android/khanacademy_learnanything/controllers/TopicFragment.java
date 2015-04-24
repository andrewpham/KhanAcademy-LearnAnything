package com.andrewpham.android.khanacademy_learnanything.controllers;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NavUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.andrewpham.android.khanacademy_learnanything.R;
import com.andrewpham.android.khanacademy_learnanything.adapters.TopicItemAdapter;
import com.andrewpham.android.khanacademy_learnanything.adapters.VideoItemAdapter;
import com.andrewpham.android.khanacademy_learnanything.api.ApiClient;
import com.andrewpham.android.khanacademy_learnanything.exercise_model.ExerciseData;
import com.andrewpham.android.khanacademy_learnanything.node_object.NodeObject;
import com.andrewpham.android.khanacademy_learnanything.topic_model.Child;
import com.andrewpham.android.khanacademy_learnanything.topic_model.TopicData;
import com.andrewpham.android.khanacademy_learnanything.video_model.VideoData;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by andrewpham on 8/26/14.
 */
public class TopicFragment extends Fragment {

    public static final String EXTRA_NODE_SLUG =
            "com.andrewpham.android.khanacademy_learnanything.controllers.node_slug";
    public static final String EXTRA_TITLE =
            "com.andrewpham.android.khanacademy_learnanything.controllers.title";
    public static final String EXTRA_ID =
            "com.andrewpham.android.khanacademy_learnanything.controllers.id";
    public static final String EXTRA_URL = "com.andrewpham.android.khanacademy_learnanything.controllers.url";
    public static final int DELAY_MILLIS = 1000;

    private static final String NODE_SLUG_TAG = "NodeSlugId";
    private String mNodeSlug;
    private ArrayList<NodeObject> mNodeObjects = new ArrayList<>();

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
                    final NodeObject nodeObject = new NodeObject();
                    if (nodeSlug.startsWith("v/") || nodeSlug.startsWith("e/") ||
                            nodeSlug.startsWith("a/") || nodeSlug.startsWith("p/")) continue;
                    nodeObject.setNodeSlug(nodeSlug);
                    ApiClient.get().getTopicData(nodeSlug, new Callback<TopicData>() {
                        @Override
                        public void success(final TopicData topicData, Response response) {
                            nodeObject.setTitle(topicData.getTitle());
                            nodeObject.setDescription(topicData.getDescription());
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
                    mNodeObjects.add(nodeObject);
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
                    NodeObject nodeObject = new NodeObject();
                    nodeObject.setNodeSlug(nodeSlug);
                    nodeObject.setTitle(videoData.getTitle());
                    nodeObject.setDescription(videoData.getDescription());
                    int duration = videoData.getDuration();
                    String remainder = Integer.toString(duration % 60);
                    String seconds = (Integer.parseInt(remainder) < 10) ? "0" + remainder : remainder;
                    nodeObject.setDuration(Integer.toString(duration / 60) +
                            ":" + seconds);
                    try {
                        nodeObject.setDateAdded(new SimpleDateFormat("yyyy-MM-dd")
                                .parse(videoData.getDateAdded().substring(0, 10)));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    nodeObject.setId(videoData.getYoutubeId());
                    nodeObject.setImageUrl(videoData.getImageUrl());
                    nodeObject.setDownloadUrl(videoData.getDownloadUrls().getMp4());
                    if (mVideoItemAdapter == null) {
                        setupAdapter();
                    } else {
                        mVideoItemAdapter.notifyDataSetChanged();
                    }
                    mNodeObjects.add(nodeObject);
                }
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
        ApiClient.get().getTopicExercises(mNodeSlug, new Callback<List<ExerciseData>>() {
            @Override
            public void success(List<ExerciseData> exerciseDataList, Response response) {
                for (ExerciseData exerciseData : exerciseDataList) {
                    String nodeSlug = exerciseData.getNodeSlug();
                    NodeObject nodeObject = new NodeObject();
                    nodeObject.setNodeSlug(nodeSlug);
                    nodeObject.setTitle(exerciseData.getTitle());
                    nodeObject.setDescription(exerciseData.getDescription());
                    try {
                        nodeObject.setDateAdded(new SimpleDateFormat("yyyy-MM-dd")
                                .parse(exerciseData.getCreationDate().substring(0, 10)));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    nodeObject.setImageUrl(exerciseData.getImageUrl256());
                    nodeObject.setKaUrl(exerciseData.getKaUrl());
                    if (mVideoItemAdapter == null) {
                        setupAdapter();
                    } else {
                        mVideoItemAdapter.notifyDataSetChanged();
                    }
                    mNodeObjects.add(nodeObject);
                }
            }

            @Override
            public void failure(RetrofitError error) {

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

        if (mNodeObjects.size() > 0) {
            if (mNodeObjects.get(0).getNodeSlug().startsWith("v/") ||
                    mNodeObjects.get(0).getNodeSlug().startsWith("e/")) {
                mVideoItemAdapter = new VideoItemAdapter(getActivity(), getActivity(), mNodeObjects);
                mListView.setAdapter(mVideoItemAdapter);
            } else {
                mTopicItemAdapter = new TopicItemAdapter(getActivity(), getActivity(), mNodeObjects);
                mListView.setAdapter(mTopicItemAdapter);
            }
        } else {
            mListView.setAdapter(null);
        }
    }

    public static boolean isAClick(float startX, float endX, float startY, float endY) {
        float differenceX = Math.abs(startX - endX);
        float differenceY = Math.abs(startY - endY);
        if (differenceX > 10 || differenceY > 10) {
            return false;
        }
        return true;
    }

}
