package com.andrewpham.android.khanacademy_learnanything.video_list_model;

import com.google.gson.annotations.Expose;

public class DownloadUrls {

    @Expose
    private String mp4;
    @Expose
    private String png;
    @Expose
    private String m3u8;

    public String getMp4() {
        return mp4;
    }

    public void setMp4(String mp4) {
        this.mp4 = mp4;
    }

    public String getPng() {
        return png;
    }

    public void setPng(String png) {
        this.png = png;
    }

    public String getM3u8() {
        return m3u8;
    }

    public void setM3u8(String m3u8) {
        this.m3u8 = m3u8;
    }

}