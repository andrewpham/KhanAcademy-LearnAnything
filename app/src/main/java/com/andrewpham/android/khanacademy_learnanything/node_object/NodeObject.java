package com.andrewpham.android.khanacademy_learnanything.node_object;

import java.util.Date;

/**
 * Created by andrewpham on 1/14/15.
 */
public class NodeObject {

    private String nodeSlug;
    private String title;
    private String description;
    private String duration;
    private Date dateAdded;
    private String id;
    private String imageUrl;
    private String downloadUrl;

    public String getNodeSlug() {
        return nodeSlug;
    }

    public void setNodeSlug(String nodeSlug) {
        this.nodeSlug = nodeSlug;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

}
