package com.andrewpham.android.khanacademy_learnanything.topic_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Child {

    @Expose
    private String kind;
    @Expose
    private boolean hide;
    @Expose
    private String key;
    @SerializedName("internal_id")
    @Expose
    private String internalId;
    @Expose
    private String title;
    @Expose
    private String url;
    @SerializedName("translated_title")
    @Expose
    private String translatedTitle;
    @SerializedName("node_slug")
    @Expose
    private String nodeSlug;
    @Expose
    private String id;
    @SerializedName("edit_slug")
    @Expose
    private String editSlug;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public boolean isHide() {
        return hide;
    }

    public void setHide(boolean hide) {
        this.hide = hide;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getInternalId() {
        return internalId;
    }

    public void setInternalId(String internalId) {
        this.internalId = internalId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTranslatedTitle() {
        return translatedTitle;
    }

    public void setTranslatedTitle(String translatedTitle) {
        this.translatedTitle = translatedTitle;
    }

    public String getNodeSlug() {
        return nodeSlug;
    }

    public void setNodeSlug(String nodeSlug) {
        this.nodeSlug = nodeSlug;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEditSlug() {
        return editSlug;
    }

    public void setEditSlug(String editSlug) {
        this.editSlug = editSlug;
    }

}