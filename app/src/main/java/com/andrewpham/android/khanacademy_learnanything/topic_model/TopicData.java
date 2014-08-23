package com.andrewpham.android.khanacademy_learnanything.topic_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class TopicData {

    @SerializedName("icon_src")
    @Expose
    private String iconSrc;
    @SerializedName("domain_slug")
    @Expose
    private String domainSlug;
    @SerializedName("relative_url")
    @Expose
    private String relativeUrl;
    @SerializedName("creation_date")
    @Expose
    private String creationDate;
    @SerializedName("web_url")
    @Expose
    private String webUrl;
    @SerializedName("ka_url")
    @Expose
    private String kaUrl;
    @SerializedName("translated_standalone_title")
    @Expose
    private String translatedStandaloneTitle;
    @SerializedName("translated_title")
    @Expose
    private String translatedTitle;
    @SerializedName("author_key")
    @Expose
    private String authorKey;
    @SerializedName("gplus_url")
    @Expose
    private String gplusUrl;
    @Expose
    private String id;
    @SerializedName("old_key_name")
    @Expose
    private String oldKeyName;
    @Expose
    private boolean hide;
    @SerializedName("do_not_publish")
    @Expose
    private boolean doNotPublish;
    @SerializedName("child_data")
    @Expose
    private List<ChildDatum> childData = new ArrayList<ChildDatum>();
    @Expose
    private List<Child> children = new ArrayList<Child>();
    @SerializedName("twitter_url")
    @Expose
    private String twitterUrl;
    @SerializedName("translated_description")
    @Expose
    private String translatedDescription;
    @Expose
    private Object version;
    @SerializedName("alternate_slugs")
    @Expose
    private List<Object> alternateSlugs = new ArrayList<Object>();
    @SerializedName("deleted_mod_time")
    @Expose
    private String deletedModTime;
    @SerializedName("logo_image_url")
    @Expose
    private String logoImageUrl;
    @SerializedName("in_knowledge_map")
    @Expose
    private boolean inKnowledgeMap;
    @Expose
    private String description;
    @SerializedName("x_pos")
    @Expose
    private int xPos;
    @SerializedName("node_slug")
    @Expose
    private String nodeSlug;
    @Expose
    private boolean deleted;
    @SerializedName("listed_locales")
    @Expose
    private List<String> listedLocales = new ArrayList<String>();
    @SerializedName("facebook_url")
    @Expose
    private String facebookUrl;
    @SerializedName("backup_timestamp")
    @Expose
    private String backupTimestamp;
    @SerializedName("render_type")
    @Expose
    private String renderType;
    @SerializedName("background_image_url")
    @Expose
    private String backgroundImageUrl;
    @SerializedName("background_image_caption")
    @Expose
    private String backgroundImageCaption;
    @SerializedName("topic_page_url")
    @Expose
    private String topicPageUrl;
    @SerializedName("extended_slug")
    @Expose
    private String extendedSlug;
    @Expose
    private String slug;
    @Expose
    private List<String> tags = new ArrayList<String>();
    @Expose
    private String kind;
    @SerializedName("in_topic_browser")
    @Expose
    private boolean inTopicBrowser;
    @Expose
    private String title;
    @Expose
    private String sha;
    @SerializedName("standalone_title")
    @Expose
    private String standaloneTitle;
    @SerializedName("y_pos")
    @Expose
    private int yPos;
    @SerializedName("current_revision_key")
    @Expose
    private String currentRevisionKey;
    @SerializedName("content_id")
    @Expose
    private String contentId;
    @SerializedName("content_kind")
    @Expose
    private String contentKind;

    public String getIconSrc() {
        return iconSrc;
    }

    public void setIconSrc(String iconSrc) {
        this.iconSrc = iconSrc;
    }

    public String getDomainSlug() {
        return domainSlug;
    }

    public void setDomainSlug(String domainSlug) {
        this.domainSlug = domainSlug;
    }

    public String getRelativeUrl() {
        return relativeUrl;
    }

    public void setRelativeUrl(String relativeUrl) {
        this.relativeUrl = relativeUrl;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public String getKaUrl() {
        return kaUrl;
    }

    public void setKaUrl(String kaUrl) {
        this.kaUrl = kaUrl;
    }

    public String getTranslatedStandaloneTitle() {
        return translatedStandaloneTitle;
    }

    public void setTranslatedStandaloneTitle(String translatedStandaloneTitle) {
        this.translatedStandaloneTitle = translatedStandaloneTitle;
    }

    public String getTranslatedTitle() {
        return translatedTitle;
    }

    public void setTranslatedTitle(String translatedTitle) {
        this.translatedTitle = translatedTitle;
    }

    public String getAuthorKey() {
        return authorKey;
    }

    public void setAuthorKey(String authorKey) {
        this.authorKey = authorKey;
    }

    public String getGplusUrl() {
        return gplusUrl;
    }

    public void setGplusUrl(String gplusUrl) {
        this.gplusUrl = gplusUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOldKeyName() {
        return oldKeyName;
    }

    public void setOldKeyName(String oldKeyName) {
        this.oldKeyName = oldKeyName;
    }

    public boolean isHide() {
        return hide;
    }

    public void setHide(boolean hide) {
        this.hide = hide;
    }

    public boolean isDoNotPublish() {
        return doNotPublish;
    }

    public void setDoNotPublish(boolean doNotPublish) {
        this.doNotPublish = doNotPublish;
    }

    public List<ChildDatum> getChildData() {
        return childData;
    }

    public void setChildData(List<ChildDatum> childData) {
        this.childData = childData;
    }

    public List<Child> getChildren() {
        return children;
    }

    public void setChildren(List<Child> children) {
        this.children = children;
    }

    public String getTwitterUrl() {
        return twitterUrl;
    }

    public void setTwitterUrl(String twitterUrl) {
        this.twitterUrl = twitterUrl;
    }

    public String getTranslatedDescription() {
        return translatedDescription;
    }

    public void setTranslatedDescription(String translatedDescription) {
        this.translatedDescription = translatedDescription;
    }

    public Object getVersion() {
        return version;
    }

    public void setVersion(Object version) {
        this.version = version;
    }

    public List<Object> getAlternateSlugs() {
        return alternateSlugs;
    }

    public void setAlternateSlugs(List<Object> alternateSlugs) {
        this.alternateSlugs = alternateSlugs;
    }

    public String getDeletedModTime() {
        return deletedModTime;
    }

    public void setDeletedModTime(String deletedModTime) {
        this.deletedModTime = deletedModTime;
    }

    public String getLogoImageUrl() {
        return logoImageUrl;
    }

    public void setLogoImageUrl(String logoImageUrl) {
        this.logoImageUrl = logoImageUrl;
    }

    public boolean isInKnowledgeMap() {
        return inKnowledgeMap;
    }

    public void setInKnowledgeMap(boolean inKnowledgeMap) {
        this.inKnowledgeMap = inKnowledgeMap;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getXPos() {
        return xPos;
    }

    public void setXPos(int xPos) {
        this.xPos = xPos;
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

    public List<String> getListedLocales() {
        return listedLocales;
    }

    public void setListedLocales(List<String> listedLocales) {
        this.listedLocales = listedLocales;
    }

    public String getFacebookUrl() {
        return facebookUrl;
    }

    public void setFacebookUrl(String facebookUrl) {
        this.facebookUrl = facebookUrl;
    }

    public String getBackupTimestamp() {
        return backupTimestamp;
    }

    public void setBackupTimestamp(String backupTimestamp) {
        this.backupTimestamp = backupTimestamp;
    }

    public String getRenderType() {
        return renderType;
    }

    public void setRenderType(String renderType) {
        this.renderType = renderType;
    }

    public String getBackgroundImageUrl() {
        return backgroundImageUrl;
    }

    public void setBackgroundImageUrl(String backgroundImageUrl) {
        this.backgroundImageUrl = backgroundImageUrl;
    }

    public String getBackgroundImageCaption() {
        return backgroundImageCaption;
    }

    public void setBackgroundImageCaption(String backgroundImageCaption) {
        this.backgroundImageCaption = backgroundImageCaption;
    }

    public String getTopicPageUrl() {
        return topicPageUrl;
    }

    public void setTopicPageUrl(String topicPageUrl) {
        this.topicPageUrl = topicPageUrl;
    }

    public String getExtendedSlug() {
        return extendedSlug;
    }

    public void setExtendedSlug(String extendedSlug) {
        this.extendedSlug = extendedSlug;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public boolean isInTopicBrowser() {
        return inTopicBrowser;
    }

    public void setInTopicBrowser(boolean inTopicBrowser) {
        this.inTopicBrowser = inTopicBrowser;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSha() {
        return sha;
    }

    public void setSha(String sha) {
        this.sha = sha;
    }

    public String getStandaloneTitle() {
        return standaloneTitle;
    }

    public void setStandaloneTitle(String standaloneTitle) {
        this.standaloneTitle = standaloneTitle;
    }

    public int getYPos() {
        return yPos;
    }

    public void setYPos(int yPos) {
        this.yPos = yPos;
    }

    public String getCurrentRevisionKey() {
        return currentRevisionKey;
    }

    public void setCurrentRevisionKey(String currentRevisionKey) {
        this.currentRevisionKey = currentRevisionKey;
    }

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public String getContentKind() {
        return contentKind;
    }

    public void setContentKind(String contentKind) {
        this.contentKind = contentKind;
    }

}