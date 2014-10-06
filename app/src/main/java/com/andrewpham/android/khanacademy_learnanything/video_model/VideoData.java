package com.andrewpham.android.khanacademy_learnanything.video_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class VideoData {

    @SerializedName("translated_youtube_id")
    @Expose
    private String translatedYoutubeId;
    @SerializedName("relative_url")
    @Expose
    private String relativeUrl;
    @SerializedName("license_logo_url")
    @Expose
    private String licenseLogoUrl;
    @SerializedName("has_questions")
    @Expose
    private String hasQuestions;
    @Expose
    private String keywords;
    @SerializedName("ka_url")
    @Expose
    private String kaUrl;
    @Expose
    private int duration;
    @SerializedName("translated_title")
    @Expose
    private String translatedTitle;
    @SerializedName("translated_description_html")
    @Expose
    private String translatedDescriptionHtml;
    @Expose
    private String id;
    @SerializedName("description_html")
    @Expose
    private String descriptionHtml;
    @Expose
    private String title;
    @SerializedName("progress_key")
    @Expose
    private String progressKey;
    @SerializedName("edit_slug")
    @Expose
    private String editSlug;
    @SerializedName("author_names")
    @Expose
    private List<String> authorNames = new ArrayList<String>();
    @SerializedName("license_full_name")
    @Expose
    private String licenseFullName;
    @SerializedName("license_url")
    @Expose
    private String licenseUrl;
    @SerializedName("deleted_mod_time")
    @Expose
    private String deletedModTime;
    @Expose
    private String description;
    @SerializedName("extra_properties")
    @Expose
    private Object extraProperties;
    @SerializedName("node_slug")
    @Expose
    private String nodeSlug;
    @Expose
    private boolean deleted;
    @SerializedName("license_name")
    @Expose
    private String licenseName;
    @SerializedName("backup_timestamp")
    @Expose
    private String backupTimestamp;
    @SerializedName("date_added")
    @Expose
    private String dateAdded;
    @SerializedName("download_urls")
    @Expose
    private DownloadUrls downloadUrls;
    @SerializedName("translated_youtube_lang")
    @Expose
    private String translatedYoutubeLang;
    @Expose
    private String kind;
    @Expose
    private String url;
    @SerializedName("clarifications_enabled")
    @Expose
    private boolean clarificationsEnabled;
    @SerializedName("ka_user_license")
    @Expose
    private String kaUserLicense;
    @SerializedName("global_id")
    @Expose
    private String globalId;
    @Expose
    private String sha;
    @SerializedName("translated_description")
    @Expose
    private String translatedDescription;
    @SerializedName("image_url")
    @Expose
    private String imageUrl;
    @SerializedName("youtube_id")
    @Expose
    private String youtubeId;
    @Expose
    private int position;
    @SerializedName("content_kind")
    @Expose
    private String contentKind;
    @SerializedName("readable_id")
    @Expose
    private String readableId;

    public String getTranslatedYoutubeId() {
        return translatedYoutubeId;
    }

    public void setTranslatedYoutubeId(String translatedYoutubeId) {
        this.translatedYoutubeId = translatedYoutubeId;
    }

    public String getRelativeUrl() {
        return relativeUrl;
    }

    public void setRelativeUrl(String relativeUrl) {
        this.relativeUrl = relativeUrl;
    }

    public String getLicenseLogoUrl() {
        return licenseLogoUrl;
    }

    public void setLicenseLogoUrl(String licenseLogoUrl) {
        this.licenseLogoUrl = licenseLogoUrl;
    }

    public String getHasQuestions() {
        return hasQuestions;
    }

    public void setHasQuestions(String hasQuestions) {
        this.hasQuestions = hasQuestions;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getKaUrl() {
        return kaUrl;
    }

    public void setKaUrl(String kaUrl) {
        this.kaUrl = kaUrl;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getTranslatedTitle() {
        return translatedTitle;
    }

    public void setTranslatedTitle(String translatedTitle) {
        this.translatedTitle = translatedTitle;
    }

    public String getTranslatedDescriptionHtml() {
        return translatedDescriptionHtml;
    }

    public void setTranslatedDescriptionHtml(String translatedDescriptionHtml) {
        this.translatedDescriptionHtml = translatedDescriptionHtml;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescriptionHtml() {
        return descriptionHtml;
    }

    public void setDescriptionHtml(String descriptionHtml) {
        this.descriptionHtml = descriptionHtml;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProgressKey() {
        return progressKey;
    }

    public void setProgressKey(String progressKey) {
        this.progressKey = progressKey;
    }

    public String getEditSlug() {
        return editSlug;
    }

    public void setEditSlug(String editSlug) {
        this.editSlug = editSlug;
    }

    public List<String> getAuthorNames() {
        return authorNames;
    }

    public void setAuthorNames(List<String> authorNames) {
        this.authorNames = authorNames;
    }

    public String getLicenseFullName() {
        return licenseFullName;
    }

    public void setLicenseFullName(String licenseFullName) {
        this.licenseFullName = licenseFullName;
    }

    public String getLicenseUrl() {
        return licenseUrl;
    }

    public void setLicenseUrl(String licenseUrl) {
        this.licenseUrl = licenseUrl;
    }

    public String getDeletedModTime() {
        return deletedModTime;
    }

    public void setDeletedModTime(String deletedModTime) {
        this.deletedModTime = deletedModTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getExtraProperties() {
        return extraProperties;
    }

    public void setExtraProperties(Object extraProperties) {
        this.extraProperties = extraProperties;
    }

    public String getNodeSlug() {
        return nodeSlug;
    }

    public void setNodeSlug(String nodeSlug) {
        this.nodeSlug = nodeSlug;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public String getLicenseName() {
        return licenseName;
    }

    public void setLicenseName(String licenseName) {
        this.licenseName = licenseName;
    }

    public String getBackupTimestamp() {
        return backupTimestamp;
    }

    public void setBackupTimestamp(String backupTimestamp) {
        this.backupTimestamp = backupTimestamp;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    public DownloadUrls getDownloadUrls() {
        return downloadUrls;
    }

    public void setDownloadUrls(DownloadUrls downloadUrls) {
        this.downloadUrls = downloadUrls;
    }

    public String getTranslatedYoutubeLang() {
        return translatedYoutubeLang;
    }

    public void setTranslatedYoutubeLang(String translatedYoutubeLang) {
        this.translatedYoutubeLang = translatedYoutubeLang;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isClarificationsEnabled() {
        return clarificationsEnabled;
    }

    public void setClarificationsEnabled(boolean clarificationsEnabled) {
        this.clarificationsEnabled = clarificationsEnabled;
    }

    public String getKaUserLicense() {
        return kaUserLicense;
    }

    public void setKaUserLicense(String kaUserLicense) {
        this.kaUserLicense = kaUserLicense;
    }

    public String getGlobalId() {
        return globalId;
    }

    public void setGlobalId(String globalId) {
        this.globalId = globalId;
    }

    public String getSha() {
        return sha;
    }

    public void setSha(String sha) {
        this.sha = sha;
    }

    public String getTranslatedDescription() {
        return translatedDescription;
    }

    public void setTranslatedDescription(String translatedDescription) {
        this.translatedDescription = translatedDescription;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getYoutubeId() {
        return youtubeId;
    }

    public void setYoutubeId(String youtubeId) {
        this.youtubeId = youtubeId;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getContentKind() {
        return contentKind;
    }

    public void setContentKind(String contentKind) {
        this.contentKind = contentKind;
    }

    public String getReadableId() {
        return readableId;
    }

    public void setReadableId(String readableId) {
        this.readableId = readableId;
    }

}