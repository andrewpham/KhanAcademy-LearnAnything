package com.andrewpham.android.khanacademy_learnanything.video_model;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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
    private boolean hasQuestions;
    @SerializedName("creation_date")
    @Expose
    private String creationDate;
    @SerializedName("download_urls")
    @Expose
    private DownloadUrls downloadUrls;
    @SerializedName("ka_url")
    @Expose
    private String kaUrl;
    @Expose
    private int duration;
    @SerializedName("translated_title")
    @Expose
    private String translatedTitle;
    @SerializedName("author_key")
    @Expose
    private Object authorKey;
    @SerializedName("translated_description_html")
    @Expose
    private String translatedDescriptionHtml;
    @Expose
    private String id;
    @SerializedName("description_html")
    @Expose
    private String descriptionHtml;
    @SerializedName("do_not_publish")
    @Expose
    private boolean doNotPublish;
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
    @Expose
    private int position;
    @SerializedName("date_added")
    @Expose
    private String dateAdded;
    @SerializedName("translated_youtube_lang")
    @Expose
    private String translatedYoutubeLang;
    @Expose
    private String kind;
    @SerializedName("date_modified")
    @Expose
    private String dateModified;
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
    @Expose
    private String keywords;
    @SerializedName("youtube_id")
    @Expose
    private String youtubeId;
    @Expose
    private String title;
    @SerializedName("content_id")
    @Expose
    private String contentId;
    @SerializedName("content_kind")
    @Expose
    private String contentKind;
    @SerializedName("readable_id")
    @Expose
    private String readableId;

    /**
     * @return The translatedYoutubeId
     */
    public String getTranslatedYoutubeId() {
        return translatedYoutubeId;
    }

    /**
     * @param translatedYoutubeId The translated_youtube_id
     */
    public void setTranslatedYoutubeId(String translatedYoutubeId) {
        this.translatedYoutubeId = translatedYoutubeId;
    }

    /**
     * @return The relativeUrl
     */
    public String getRelativeUrl() {
        return relativeUrl;
    }

    /**
     * @param relativeUrl The relative_url
     */
    public void setRelativeUrl(String relativeUrl) {
        this.relativeUrl = relativeUrl;
    }

    /**
     * @return The licenseLogoUrl
     */
    public String getLicenseLogoUrl() {
        return licenseLogoUrl;
    }

    /**
     * @param licenseLogoUrl The license_logo_url
     */
    public void setLicenseLogoUrl(String licenseLogoUrl) {
        this.licenseLogoUrl = licenseLogoUrl;
    }

    /**
     * @return The hasQuestions
     */
    public boolean isHasQuestions() {
        return hasQuestions;
    }

    /**
     * @param hasQuestions The has_questions
     */
    public void setHasQuestions(boolean hasQuestions) {
        this.hasQuestions = hasQuestions;
    }

    /**
     * @return The creationDate
     */
    public String getCreationDate() {
        return creationDate;
    }

    /**
     * @param creationDate The creation_date
     */
    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * @return The downloadUrls
     */
    public DownloadUrls getDownloadUrls() {
        return downloadUrls;
    }

    /**
     * @param downloadUrls The download_urls
     */
    public void setDownloadUrls(DownloadUrls downloadUrls) {
        this.downloadUrls = downloadUrls;
    }

    /**
     * @return The kaUrl
     */
    public String getKaUrl() {
        return kaUrl;
    }

    /**
     * @param kaUrl The ka_url
     */
    public void setKaUrl(String kaUrl) {
        this.kaUrl = kaUrl;
    }

    /**
     * @return The duration
     */
    public int getDuration() {
        return duration;
    }

    /**
     * @param duration The duration
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * @return The translatedTitle
     */
    public String getTranslatedTitle() {
        return translatedTitle;
    }

    /**
     * @param translatedTitle The translated_title
     */
    public void setTranslatedTitle(String translatedTitle) {
        this.translatedTitle = translatedTitle;
    }

    /**
     * @return The authorKey
     */
    public Object getAuthorKey() {
        return authorKey;
    }

    /**
     * @param authorKey The author_key
     */
    public void setAuthorKey(Object authorKey) {
        this.authorKey = authorKey;
    }

    /**
     * @return The translatedDescriptionHtml
     */
    public String getTranslatedDescriptionHtml() {
        return translatedDescriptionHtml;
    }

    /**
     * @param translatedDescriptionHtml The translated_description_html
     */
    public void setTranslatedDescriptionHtml(String translatedDescriptionHtml) {
        this.translatedDescriptionHtml = translatedDescriptionHtml;
    }

    /**
     * @return The id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return The descriptionHtml
     */
    public String getDescriptionHtml() {
        return descriptionHtml;
    }

    /**
     * @param descriptionHtml The description_html
     */
    public void setDescriptionHtml(String descriptionHtml) {
        this.descriptionHtml = descriptionHtml;
    }

    /**
     * @return The doNotPublish
     */
    public boolean isDoNotPublish() {
        return doNotPublish;
    }

    /**
     * @param doNotPublish The do_not_publish
     */
    public void setDoNotPublish(boolean doNotPublish) {
        this.doNotPublish = doNotPublish;
    }

    /**
     * @return The progressKey
     */
    public String getProgressKey() {
        return progressKey;
    }

    /**
     * @param progressKey The progress_key
     */
    public void setProgressKey(String progressKey) {
        this.progressKey = progressKey;
    }

    /**
     * @return The editSlug
     */
    public String getEditSlug() {
        return editSlug;
    }

    /**
     * @param editSlug The edit_slug
     */
    public void setEditSlug(String editSlug) {
        this.editSlug = editSlug;
    }

    /**
     * @return The authorNames
     */
    public List<String> getAuthorNames() {
        return authorNames;
    }

    /**
     * @param authorNames The author_names
     */
    public void setAuthorNames(List<String> authorNames) {
        this.authorNames = authorNames;
    }

    /**
     * @return The licenseFullName
     */
    public String getLicenseFullName() {
        return licenseFullName;
    }

    /**
     * @param licenseFullName The license_full_name
     */
    public void setLicenseFullName(String licenseFullName) {
        this.licenseFullName = licenseFullName;
    }

    /**
     * @return The licenseUrl
     */
    public String getLicenseUrl() {
        return licenseUrl;
    }

    /**
     * @param licenseUrl The license_url
     */
    public void setLicenseUrl(String licenseUrl) {
        this.licenseUrl = licenseUrl;
    }

    /**
     * @return The deletedModTime
     */
    public String getDeletedModTime() {
        return deletedModTime;
    }

    /**
     * @param deletedModTime The deleted_mod_time
     */
    public void setDeletedModTime(String deletedModTime) {
        this.deletedModTime = deletedModTime;
    }

    /**
     * @return The description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return The extraProperties
     */
    public Object getExtraProperties() {
        return extraProperties;
    }

    /**
     * @param extraProperties The extra_properties
     */
    public void setExtraProperties(Object extraProperties) {
        this.extraProperties = extraProperties;
    }

    /**
     * @return The nodeSlug
     */
    public String getNodeSlug() {
        return nodeSlug;
    }

    /**
     * @param nodeSlug The node_slug
     */
    public void setNodeSlug(String nodeSlug) {
        this.nodeSlug = nodeSlug;
    }

    /**
     * @return The deleted
     */
    public boolean isDeleted() {
        return deleted;
    }

    /**
     * @param deleted The deleted
     */
    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    /**
     * @return The licenseName
     */
    public String getLicenseName() {
        return licenseName;
    }

    /**
     * @param licenseName The license_name
     */
    public void setLicenseName(String licenseName) {
        this.licenseName = licenseName;
    }

    /**
     * @return The backupTimestamp
     */
    public String getBackupTimestamp() {
        return backupTimestamp;
    }

    /**
     * @param backupTimestamp The backup_timestamp
     */
    public void setBackupTimestamp(String backupTimestamp) {
        this.backupTimestamp = backupTimestamp;
    }

    /**
     * @return The position
     */
    public int getPosition() {
        return position;
    }

    /**
     * @param position The position
     */
    public void setPosition(int position) {
        this.position = position;
    }

    /**
     * @return The dateAdded
     */
    public String getDateAdded() {
        return dateAdded;
    }

    /**
     * @param dateAdded The date_added
     */
    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    /**
     * @return The translatedYoutubeLang
     */
    public String getTranslatedYoutubeLang() {
        return translatedYoutubeLang;
    }

    /**
     * @param translatedYoutubeLang The translated_youtube_lang
     */
    public void setTranslatedYoutubeLang(String translatedYoutubeLang) {
        this.translatedYoutubeLang = translatedYoutubeLang;
    }

    /**
     * @return The kind
     */
    public String getKind() {
        return kind;
    }

    /**
     * @param kind The kind
     */
    public void setKind(String kind) {
        this.kind = kind;
    }

    /**
     * @return The dateModified
     */
    public String getDateModified() {
        return dateModified;
    }

    /**
     * @param dateModified The date_modified
     */
    public void setDateModified(String dateModified) {
        this.dateModified = dateModified;
    }

    /**
     * @return The url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url The url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return The clarificationsEnabled
     */
    public boolean isClarificationsEnabled() {
        return clarificationsEnabled;
    }

    /**
     * @param clarificationsEnabled The clarifications_enabled
     */
    public void setClarificationsEnabled(boolean clarificationsEnabled) {
        this.clarificationsEnabled = clarificationsEnabled;
    }

    /**
     * @return The kaUserLicense
     */
    public String getKaUserLicense() {
        return kaUserLicense;
    }

    /**
     * @param kaUserLicense The ka_user_license
     */
    public void setKaUserLicense(String kaUserLicense) {
        this.kaUserLicense = kaUserLicense;
    }

    /**
     * @return The globalId
     */
    public String getGlobalId() {
        return globalId;
    }

    /**
     * @param globalId The global_id
     */
    public void setGlobalId(String globalId) {
        this.globalId = globalId;
    }

    /**
     * @return The sha
     */
    public String getSha() {
        return sha;
    }

    /**
     * @param sha The sha
     */
    public void setSha(String sha) {
        this.sha = sha;
    }

    /**
     * @return The translatedDescription
     */
    public String getTranslatedDescription() {
        return translatedDescription;
    }

    /**
     * @param translatedDescription The translated_description
     */
    public void setTranslatedDescription(String translatedDescription) {
        this.translatedDescription = translatedDescription;
    }

    /**
     * @return The imageUrl
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * @param imageUrl The image_url
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    /**
     * @return The keywords
     */
    public String getKeywords() {
        return keywords;
    }

    /**
     * @param keywords The keywords
     */
    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    /**
     * @return The youtubeId
     */
    public String getYoutubeId() {
        return youtubeId;
    }

    /**
     * @param youtubeId The youtube_id
     */
    public void setYoutubeId(String youtubeId) {
        this.youtubeId = youtubeId;
    }

    /**
     * @return The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return The contentId
     */
    public String getContentId() {
        return contentId;
    }

    /**
     * @param contentId The content_id
     */
    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    /**
     * @return The contentKind
     */
    public String getContentKind() {
        return contentKind;
    }

    /**
     * @param contentKind The content_kind
     */
    public void setContentKind(String contentKind) {
        this.contentKind = contentKind;
    }

    /**
     * @return The readableId
     */
    public String getReadableId() {
        return readableId;
    }

    /**
     * @param readableId The readable_id
     */
    public void setReadableId(String readableId) {
        this.readableId = readableId;
    }

}