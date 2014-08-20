package com.andrewpham.android.khanacademy_learnanything;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.andrewpham.android.khanacademy_learnanything.models.NavDrawerItem;

import java.util.ArrayList;

/**
 * Created by Andrew on 19/08/2014.
 */
public class NavDrawerListAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<NavDrawerItem> mNavDrawerItems;

    public NavDrawerListAdapter(Context context, ArrayList<NavDrawerItem> navDrawerItems) {
        mContext = context;
        mNavDrawerItems = navDrawerItems;
    }

    @Override
    public int getCount() {
        return mNavDrawerItems.size();
    }

    @Override
    public Object getItem(int i) {
        return mNavDrawerItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater)
                    mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.drawer_list_item, null);
        }

        ImageView icon = (ImageView) view.findViewById(R.id.icon);
        icon.setImageResource(mNavDrawerItems.get(i).getIcon());
        TextView title = (TextView) view.findViewById(R.id.title);
        title.setText(mNavDrawerItems.get(i).getTitle());

        return view;
    }
}
