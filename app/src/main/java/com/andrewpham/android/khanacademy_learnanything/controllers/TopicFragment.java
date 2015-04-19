package com.andrewpham.android.khanacademy_learnanything.controllers;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NavUtils;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
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
import com.andrewpham.android.khanacademy_learnanything.api.ApiClient;
import com.andrewpham.android.khanacademy_learnanything.exercise_model.ExerciseData;
import com.andrewpham.android.khanacademy_learnanything.node_object.NodeObject;
import com.andrewpham.android.khanacademy_learnanything.service.DownloadService;
import com.andrewpham.android.khanacademy_learnanything.topic_model.Child;
import com.andrewpham.android.khanacademy_learnanything.topic_model.TopicData;
import com.andrewpham.android.khanacademy_learnanything.video_model.VideoData;
import com.squareup.picasso.Picasso;

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

    private static final String NODE_SLUG_TAG = "NodeSlugId";
    private String mNodeSlug;
    private ArrayList<NodeObject> mNodeObjects = new ArrayList<NodeObject>();

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
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long id) {
                getSubtopic(pos);
            }
        });

        return v;
    }

    private void getSubtopic(int pos) {
        final NodeObject item = mNodeObjects.get(pos - 1);
        final ArrayList<String> nodeSlugs = new ArrayList<String>();
        ApiClient.get().getTopicData(item.getNodeSlug(), new Callback<TopicData>() {
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
                    i.putExtra(EXTRA_NODE_SLUG, item.getNodeSlug());
                    i.putExtra(EXTRA_TITLE, item.getTitle());

                    startActivity(i);
                }
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
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
                mVideoItemAdapter = new VideoItemAdapter(mNodeObjects);
                mListView.setAdapter(mVideoItemAdapter);
            } else {
                mTopicItemAdapter = new TopicItemAdapter(mNodeObjects);
                mListView.setAdapter(mTopicItemAdapter);
            }
        } else {
            mListView.setAdapter(null);
        }
    }

    private class TopicItemAdapter extends ArrayAdapter<NodeObject> {
        public TopicItemAdapter(ArrayList<NodeObject> items) {
            super(getActivity(), 0, items);
        }

        ViewHolder holder;

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getActivity().getLayoutInflater()
                        .inflate(R.layout.topic_list_item, parent, false);
                holder = new ViewHolder();
                holder.title = (TextView) convertView.findViewById(R.id.title);
                holder.description = (TextView) convertView.findViewById(R.id.description);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            NodeObject item = getItem(position);

            holder.title.setText(item.getTitle());

            holder.description.setText(item.getDescription());

            return convertView;
        }
    }

    @SuppressWarnings("deprecation")
    private class VideoItemAdapter extends ArrayAdapter<NodeObject> {
        public VideoItemAdapter(ArrayList<NodeObject> items) {
            super(getActivity(), 0, items);
        }

        ViewHolder holder;

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getActivity().getLayoutInflater()
                        .inflate(R.layout.video_list_item, parent, false);
                holder = new ViewHolder();
                holder.title = (TextView) convertView.findViewById(R.id.title);
                holder.description = (TextView) convertView.findViewById(R.id.description);
                holder.dateAdded = (TextView) convertView.findViewById(R.id.dateAdded);
                holder.duration = (TextView) convertView.findViewById(R.id.duration);
                holder.imageView = (ImageView) convertView
                        .findViewById(R.id.list_item_imageView);
                holder.note = (TextView) convertView.findViewById(R.id.note);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            final NodeObject item = getItem(position);

            holder.description.setText(item.getDescription());

            SimpleDateFormat ft = new SimpleDateFormat("MMMM d, yyyy");
            SpannableString dateAddedText = new SpannableString("Published:  " +
                    ft.format(item.getDateAdded()));
            dateAddedText
                    .setSpan(new ForegroundColorSpan(R.color.description_text), 10, dateAddedText.length(), Spannable
                            .SPAN_EXCLUSIVE_EXCLUSIVE);
            holder.dateAdded.setText(dateAddedText);

            ViewGroup.LayoutParams params = holder.imageView.getLayoutParams();

            if (item.getNodeSlug().startsWith("v/")) {
                holder.title.setText("Video:  " + item.getTitle());

                convertView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(getActivity(), VideoActivity.class);
                        i.putExtra(EXTRA_ID, item.getId());
                        i.putExtra(EXTRA_TITLE, item.getTitle());
                        startActivity(i);
                    }
                });

                convertView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        Intent i = new Intent(getActivity(), DownloadService.class);
                        i.putExtra(EXTRA_URL, item.getDownloadUrl());
                        i.putExtra(EXTRA_TITLE, item.getTitle());
                        getActivity().startService(i);
                        return true;
                    }
                });

                Display display = getActivity().getWindowManager().getDefaultDisplay();
                int width = display.getWidth();
                params.height = (int) (width * 297. / 396);
                params.width = width;
                holder.imageView.setLayoutParams(params);
                Picasso.with(getActivity())
                        .load(item.getImageUrl())
                        .transform(new RoundedTransformation(23, 0))
                        .fit()
                        .into(holder.imageView);

                holder.note.setText(R.string.app_note);

                SpannableString durationText = new SpannableString("Duration:  " +
                        item.getDuration());
                durationText
                        .setSpan(new ForegroundColorSpan(R.color.description_text), 9, durationText.length(), Spannable
                                .SPAN_EXCLUSIVE_EXCLUSIVE);
                holder.duration.setText(durationText);
            } else {
                holder.title.setText("Exercise:  " + item.getTitle());

                convertView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(getActivity(), WebpageActivity.class);
                        i.setData(Uri.parse(item.getKaUrl()));
                        startActivity(i);
                    }
                });

                convertView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        Intent i = new Intent(getActivity(), WebpageActivity.class);
                        i.setData(Uri.parse(item.getKaUrl()));
                        startActivity(i);
                        return true;
                    }
                });

                params.height = 0;
                params.width = 0;
                holder.imageView.setLayoutParams(params);
                holder.imageView.setImageDrawable(null);

                holder.note.setText("");

                holder.duration.setText("");
            }

            return convertView;
        }
    }

    static class ViewHolder {
        TextView title;
        TextView description;
        TextView dateAdded;
        TextView duration;
        ImageView imageView;
        TextView note;
    }

}
