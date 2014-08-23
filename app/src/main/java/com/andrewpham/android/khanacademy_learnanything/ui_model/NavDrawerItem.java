package com.andrewpham.android.khanacademy_learnanything.ui_model;

public class NavDrawerItem {

    private String mTitle;
    private int mIcon;

    public NavDrawerItem(String title, int icon) {
        this.mTitle = title;
        this.mIcon = icon;
    }

    public String getTitle() {
        return mTitle;
    }

    public int getIcon() {
        return mIcon;
    }

}
