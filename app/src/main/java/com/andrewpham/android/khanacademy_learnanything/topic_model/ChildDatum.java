package com.andrewpham.android.khanacademy_learnanything.topic_model;

import com.google.gson.annotations.Expose;

public class ChildDatum {

    @Expose
    private String kind;
    @Expose
    private String id;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
