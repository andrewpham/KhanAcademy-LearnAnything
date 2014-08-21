package com.andrewpham.android.khanacademy_learnanything.models;

public class NavDrawerItem {

    private String mTitle;
    private int mIcon;
    private String mCount = "0";
    private boolean mIsCounterVisible = false;

    public NavDrawerItem() {
    }

    public NavDrawerItem(String title, int icon) {
        this.mTitle = title;
        this.mIcon = icon;
    }

    public NavDrawerItem(String title, int icon, boolean isCounterVisible, String count) {
        this.mTitle = title;
        this.mIcon = icon;
        this.mIsCounterVisible = isCounterVisible;
        this.mCount = count;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public int getIcon() {
        return mIcon;
    }

    public void setIcon(int icon) {
        this.mIcon = icon;
    }

    public String getCount() {
        return mCount;
    }

    public void setCount(String count) {
        mCount = count;
    }

    public boolean isCounterVisible() {
        return mIsCounterVisible;
    }

    public void setCounterVisible(boolean isCounterVisible) {
        mIsCounterVisible = isCounterVisible;
    }

}
