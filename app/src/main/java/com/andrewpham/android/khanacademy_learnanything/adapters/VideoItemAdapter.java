package com.andrewpham.android.khanacademy_learnanything.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.andrewpham.android.khanacademy_learnanything.R;
import com.andrewpham.android.khanacademy_learnanything.controllers.RoundedTransformation;
import com.andrewpham.android.khanacademy_learnanything.controllers.TopicFragment;
import com.andrewpham.android.khanacademy_learnanything.controllers.VideoActivity;
import com.andrewpham.android.khanacademy_learnanything.controllers.WebpageActivity;
import com.andrewpham.android.khanacademy_learnanything.node_object.NodeObject;
import com.andrewpham.android.khanacademy_learnanything.service.DownloadService;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by andrewpham on 4/24/15.
 */
@SuppressWarnings("deprecation")
public class VideoItemAdapter extends ArrayAdapter<NodeObject> {

    Activity mActivity;
    Context mContext;
    ViewHolder holder;

    static class ViewHolder {
        TextView title;
        TextView description;
        TextView dateAdded;
        TextView duration;
        ImageView imageView;
        TextView note;
    }

    public VideoItemAdapter(Activity activity, Context context, ArrayList<NodeObject> items) {
        super(activity, 0, items);
        mActivity = activity;
        mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mActivity.getLayoutInflater()
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

        final Handler mHandler = new Handler();
        final Runnable mLongPressed = new Runnable() {
            public void run() {
                Intent i = new Intent(mActivity, DownloadService.class);
                i.putExtra(TopicFragment.EXTRA_URL, item.getDownloadUrl());
                i.putExtra(TopicFragment.EXTRA_TITLE, item.getTitle());
                mActivity.startService(i);
            }
        };

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

            Display display = mActivity.getWindowManager().getDefaultDisplay();
            int width = display.getWidth();
            params.height = (int) (width * 297. / 396);
            params.width = width;
            holder.imageView.setLayoutParams(params);
            Picasso.with(mActivity)
                    .load(item.getImageUrl())
                    .transform(new RoundedTransformation(10, 0))
                    .fit()
                    .into(holder.imageView);

            holder.note.setText(R.string.app_note);

            SpannableString durationText = new SpannableString("Duration:  " +
                    item.getDuration());
            durationText
                    .setSpan(new ForegroundColorSpan(R.color.description_text), 9, durationText.length(), Spannable
                            .SPAN_EXCLUSIVE_EXCLUSIVE);
            holder.duration.setText(durationText);

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
                            v.setPadding(v.getPaddingLeft(), v.getPaddingTop() + 4,
                                    v.getPaddingRight(), v.getPaddingBottom() - 4);
                            mHandler.postDelayed(mLongPressed, TopicFragment.DELAY_MILLIS);
                            break;
                        case MotionEvent.ACTION_UP:
                            float endX = event.getX();
                            float endY = event.getY();
                            v.setBackgroundResource(R.drawable.list_item_shape_normal);
                            v.setPadding(v.getPaddingLeft(), v.getPaddingTop() - 4,
                                    v.getPaddingRight(), v.getPaddingBottom() + 4);
                            mHandler.removeCallbacks(mLongPressed);
                            if (TopicFragment.isAClick(startX, endX, startY, endY)) {
                                v.playSoundEffect(android.view.SoundEffectConstants.CLICK);
                                Intent i = new Intent(mActivity, VideoActivity.class);
                                i.putExtra(TopicFragment.EXTRA_ID, item.getId());
                                i.putExtra(TopicFragment.EXTRA_TITLE, item.getTitle());
                                mContext.startActivity(i);
                            }
                            break;
                        case MotionEvent.ACTION_CANCEL:
                            v.setBackgroundResource(R.drawable.list_item_shape_normal);
                            v.setPadding(v.getPaddingLeft(), v.getPaddingTop() - 4,
                                    v.getPaddingRight(), v.getPaddingBottom() + 4);
                            mHandler.removeCallbacks(mLongPressed);
                            break;
                    }
                    return true;
                }
            });
        } else {
            holder.title.setText("Exercise:  " + item.getTitle());

            params.height = 0;
            params.width = 0;
            holder.imageView.setLayoutParams(params);
            holder.imageView.setImageDrawable(null);

            holder.note.setText("");

            holder.duration.setText("");

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
                                Intent i = new Intent(mActivity, WebpageActivity.class);
                                i.setData(Uri.parse(item.getKaUrl()));
                                mContext.startActivity(i);
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
        }

        return convertView;
    }

}
