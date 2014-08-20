package com.andrewpham.android.khanacademy_learnanything.models;

/**
 * Created by Andrew on 19/08/2014.
 */
public class NavDrawerItem {

    private String mTitle;
    private int mIcon;

    public NavDrawerItem() {

    }

    public NavDrawerItem(String title, int icon) {
        mTitle = title;
        mIcon = icon;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public int getIcon() {
        return mIcon;
    }

    public void setIcon(int icon) {
        mIcon = icon;
    }

}
