package com.andrewpham.android.khanacademy_learnanything.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.andrewpham.android.khanacademy_learnanything.R;
import com.andrewpham.android.khanacademy_learnanything.api.ApiClient;
import com.andrewpham.android.khanacademy_learnanything.controllers.SubtopicActivity;
import com.andrewpham.android.khanacademy_learnanything.controllers.TopicFragment;
import com.andrewpham.android.khanacademy_learnanything.node_object.NodeObject;
import com.andrewpham.android.khanacademy_learnanything.topic_model.Child;
import com.andrewpham.android.khanacademy_learnanything.topic_model.TopicData;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by andrewpham on 4/24/15.
 */
public class TopicItemAdapter extends ArrayAdapter<NodeObject> {

    Activity mActivity;
    Context mContext;
    ArrayList<NodeObject> mNodeObjects;
    ViewHolder holder;

    static class ViewHolder {
        TextView title;
        TextView description;
    }

    public TopicItemAdapter(Activity activity, Context context, ArrayList<NodeObject> items) {
        super(activity, 0, items);
        mActivity = activity;
        mContext = context;
        mNodeObjects = items;
    }

    private void getSubtopic(int pos) {
        final NodeObject item = mNodeObjects.get(pos);
        final ArrayList<String> nodeSlugs = new ArrayList<>();
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
                    Intent i = new Intent(mActivity, SubtopicActivity.class);
                    i.putExtra(TopicFragment.EXTRA_NODE_SLUG, item.getNodeSlug());
                    i.putExtra(TopicFragment.EXTRA_TITLE, item.getTitle());

                    mContext.startActivity(i);
                }
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mActivity.getLayoutInflater()
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

        convertView.setOnTouchListener(new View.OnTouchListener() {
            private float startX;
            private float startY;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        startX = event.getX();
                        startY = event.getY();
                        v.setBackgroundResource(R.drawable.list_item_shape_pressed);
                        v.setPadding(v.getPaddingLeft(), v.getPaddingTop() + 6,
                                v.getPaddingRight(), v.getPaddingBottom() - 6);
                        break;
                    case MotionEvent.ACTION_UP:
                        float endX = event.getX();
                        float endY = event.getY();
                        v.setBackgroundResource(R.drawable.list_item_shape_normal);
                        v.setPadding(v.getPaddingLeft(), v.getPaddingTop() - 6,
                                v.getPaddingRight(), v.getPaddingBottom() + 6);
                        if (TopicFragment.isAClick(startX, endX, startY, endY)) {
                            v.playSoundEffect(android.view.SoundEffectConstants.CLICK);
                            getSubtopic(position);
                        }
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        v.setBackgroundResource(R.drawable.list_item_shape_normal);
                        v.setPadding(v.getPaddingLeft(), v.getPaddingTop() - 6,
                                v.getPaddingRight(), v.getPaddingBottom() + 6);
                        break;
                }
                return true;
            }
        });

        return convertView;
    }

}
