package com.andrewpham.android.khanacademy_learnanything.exercise_model;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ExerciseData {

    @SerializedName("translated_short_display_name")
    @Expose
    private String translatedShortDisplayName;
    @SerializedName("uses_worked_examples")
    @Expose
    private boolean usesWorkedExamples;
    @SerializedName("v_position")
    @Expose
    private int vPosition;
    @SerializedName("relative_url")
    @Expose
    private String relativeUrl;
    @SerializedName("guaranteed_file_name")
    @Expose
    private String guaranteedFileName;
    @SerializedName("file_name")
    @Expose
    private String fileName;
    @SerializedName("author_name")
    @Expose
    private String authorName;
    @SerializedName("creation_date")
    @Expose
    private String creationDate;
    @SerializedName("uses_assessment_items")
    @Expose
    private boolean usesAssessmentItems;
    @SerializedName("ka_url")
    @Expose
    private String kaUrl;
    @SerializedName("short_display_name")
    @Expose
    private String shortDisplayName;
    @SerializedName("translated_title")
    @Expose
    private String translatedTitle;
    @SerializedName("author_key")
    @Expose
    private String authorKey;
    @SerializedName("translated_description_html")
    @Expose
    private String translatedDescriptionHtml;
    @Expose
    private String id;
    @SerializedName("is_quiz")
    @Expose
    private boolean isQuiz;
    @SerializedName("display_name")
    @Expose
    private String displayName;
    @SerializedName("tracking_document_url")
    @Expose
    private String trackingDocumentUrl;
    @SerializedName("description_html")
    @Expose
    private String descriptionHtml;
    @SerializedName("do_not_publish")
    @Expose
    private boolean doNotPublish;
    @Expose
    private List<Object> tags = new ArrayList<Object>();
    @SerializedName("progress_key")
    @Expose
    private String progressKey;
    @SerializedName("suggested_completion_criteria")
    @Expose
    private String suggestedCompletionCriteria;
    @SerializedName("edit_slug")
    @Expose
    private String editSlug;
    @Expose
    private boolean summative;
    @Expose
    private boolean live;
    @SerializedName("translated_description")
    @Expose
    private String translatedDescription;
    @SerializedName("pretty_display_name")
    @Expose
    private String prettyDisplayName;
    @SerializedName("deleted_mod_time")
    @Expose
    private String deletedModTime;
    @SerializedName("all_assessment_items")
    @Expose
    private List<Object> allAssessmentItems = new ArrayList<Object>();
    @Expose
    private String description;
    @SerializedName("translated_pretty_display_name")
    @Expose
    private String translatedPrettyDisplayName;
    @SerializedName("node_slug")
    @Expose
    private String nodeSlug;
    @Expose
    private boolean deleted;
    @SerializedName("backup_timestamp")
    @Expose
    private String backupTimestamp;
    @SerializedName("problem_types")
    @Expose
    private List<Object> problemTypes = new ArrayList<Object>();
    @SerializedName("curated_related_videos")
    @Expose
    private List<String> curatedRelatedVideos = new ArrayList<String>();
    @Expose
    private List<String> covers = new ArrayList<String>();
    @SerializedName("h_position")
    @Expose
    private int hPosition;
    @SerializedName("translated_display_name")
    @Expose
    private String translatedDisplayName;
    @Expose
    private String kind;
    @Expose
    private String sha1;
    @Expose
    private String name;
    @Expose
    private List<String> prerequisites = new ArrayList<String>();
    @SerializedName("assessment_item_tags")
    @Expose
    private List<String> assessmentItemTags = new ArrayList<String>();
    @Expose
    private String title;
    @SerializedName("global_id")
    @Expose
    private String globalId;
    @SerializedName("seconds_per_fast_problem")
    @Expose
    private int secondsPerFastProblem;
    @Expose
    private String sha;
    @SerializedName("image_url")
    @Expose
    private String imageUrl;
    @SerializedName("tutorial_only")
    @Expose
    private boolean tutorialOnly;
    @SerializedName("current_revision_key")
    @Expose
    private String currentRevisionKey;
    @SerializedName("image_url_256")
    @Expose
    private String imageUrl256;
    @SerializedName("content_id")
    @Expose
    private String contentId;
    @SerializedName("content_kind")
    @Expose
    private String contentKind;
    @SerializedName("date_modified")
    @Expose
    private String dateModified;

    /**
     * @return The translatedShortDisplayName
     */
    public String getTranslatedShortDisplayName() {
        return translatedShortDisplayName;
    }

    /**
     * @param translatedShortDisplayName The translated_short_display_name
     */
    public void setTranslatedShortDisplayName(String translatedShortDisplayName) {
        this.translatedShortDisplayName = translatedShortDisplayName;
    }

    /**
     * @return The usesWorkedExamples
     */
    public boolean isUsesWorkedExamples() {
        return usesWorkedExamples;
    }

    /**
     * @param usesWorkedExamples The uses_worked_examples
     */
    public void setUsesWorkedExamples(boolean usesWorkedExamples) {
        this.usesWorkedExamples = usesWorkedExamples;
    }

    /**
     * @return The vPosition
     */
    public int getVPosition() {
        return vPosition;
    }

    /**
     * @param vPosition The v_position
     */
    public void setVPosition(int vPosition) {
        this.vPosition = vPosition;
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
     * @return The guaranteedFileName
     */
    public String getGuaranteedFileName() {
        return guaranteedFileName;
    }

    /**
     * @param guaranteedFileName The guaranteed_file_name
     */
    public void setGuaranteedFileName(String guaranteedFileName) {
        this.guaranteedFileName = guaranteedFileName;
    }

    /**
     * @return The fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * @param fileName The file_name
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * @return The authorName
     */
    public String getAuthorName() {
        return authorName;
    }

    /**
     * @param authorName The author_name
     */
    public void setAuthorName(String authorName) {
        this.authorName = authorName;
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
     * @return The usesAssessmentItems
     */
    public boolean isUsesAssessmentItems() {
        return usesAssessmentItems;
    }

    /**
     * @param usesAssessmentItems The uses_assessment_items
     */
    public void setUsesAssessmentItems(boolean usesAssessmentItems) {
        this.usesAssessmentItems = usesAssessmentItems;
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
     * @return The shortDisplayName
     */
    public String getShortDisplayName() {
        return shortDisplayName;
    }

    /**
     * @param shortDisplayName The short_display_name
     */
    public void setShortDisplayName(String shortDisplayName) {
        this.shortDisplayName = shortDisplayName;
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
    public String getAuthorKey() {
        return authorKey;
    }

    /**
     * @param authorKey The author_key
     */
    public void setAuthorKey(String authorKey) {
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
     * @return The isQuiz
     */
    public boolean isIsQuiz() {
        return isQuiz;
    }

    /**
     * @param isQuiz The is_quiz
     */
    public void setIsQuiz(boolean isQuiz) {
        this.isQuiz = isQuiz;
    }

    /**
     * @return The displayName
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * @param displayName The display_name
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    /**
     * @return The trackingDocumentUrl
     */
    public String getTrackingDocumentUrl() {
        return trackingDocumentUrl;
    }

    /**
     * @param trackingDocumentUrl The tracking_document_url
     */
    public void setTrackingDocumentUrl(String trackingDocumentUrl) {
        this.trackingDocumentUrl = trackingDocumentUrl;
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
     * @return The tags
     */
    public List<Object> getTags() {
        return tags;
    }

    /**
     * @param tags The tags
     */
    public void setTags(List<Object> tags) {
        this.tags = tags;
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
     * @return The suggestedCompletionCriteria
     */
    public String getSuggestedCompletionCriteria() {
        return suggestedCompletionCriteria;
    }

    /**
     * @param suggestedCompletionCriteria The suggested_completion_criteria
     */
    public void setSuggestedCompletionCriteria(String suggestedCompletionCriteria) {
        this.suggestedCompletionCriteria = suggestedCompletionCriteria;
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
     * @return The summative
     */
    public boolean isSummative() {
        return summative;
    }

    /**
     * @param summative The summative
     */
    public void setSummative(boolean summative) {
        this.summative = summative;
    }

    /**
     * @return The live
     */
    public boolean isLive() {
        return live;
    }

    /**
     * @param live The live
     */
    public void setLive(boolean live) {
        this.live = live;
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
     * @return The prettyDisplayName
     */
    public String getPrettyDisplayName() {
        return prettyDisplayName;
    }

    /**
     * @param prettyDisplayName The pretty_display_name
     */
    public void setPrettyDisplayName(String prettyDisplayName) {
        this.prettyDisplayName = prettyDisplayName;
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
     * @return The allAssessmentItems
     */
    public List<Object> getAllAssessmentItems() {
        return allAssessmentItems;
    }

    /**
     * @param allAssessmentItems The all_assessment_items
     */
    public void setAllAssessmentItems(List<Object> allAssessmentItems) {
        this.allAssessmentItems = allAssessmentItems;
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
     * @return The translatedPrettyDisplayName
     */
    public String getTranslatedPrettyDisplayName() {
        return translatedPrettyDisplayName;
    }

    /**
     * @param translatedPrettyDisplayName The translated_pretty_display_name
     */
    public void setTranslatedPrettyDisplayName(String translatedPrettyDisplayName) {
        this.translatedPrettyDisplayName = translatedPrettyDisplayName;
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
     * @return The problemTypes
     */
    public List<Object> getProblemTypes() {
        return problemTypes;
    }

    /**
     * @param problemTypes The problem_types
     */
    public void setProblemTypes(List<Object> problemTypes) {
        this.problemTypes = problemTypes;
    }

    /**
     * @return The curatedRelatedVideos
     */
    public List<String> getCuratedRelatedVideos() {
        return curatedRelatedVideos;
    }

    /**
     * @param curatedRelatedVideos The curated_related_videos
     */
    public void setCuratedRelatedVideos(List<String> curatedRelatedVideos) {
        this.curatedRelatedVideos = curatedRelatedVideos;
    }

    /**
     * @return The covers
     */
    public List<String> getCovers() {
        return covers;
    }

    /**
     * @param covers The covers
     */
    public void setCovers(List<String> covers) {
        this.covers = covers;
    }

    /**
     * @return The hPosition
     */
    public int getHPosition() {
        return hPosition;
    }

    /**
     * @param hPosition The h_position
     */
    public void setHPosition(int hPosition) {
        this.hPosition = hPosition;
    }

    /**
     * @return The translatedDisplayName
     */
    public String getTranslatedDisplayName() {
        return translatedDisplayName;
    }

    /**
     * @param translatedDisplayName The translated_display_name
     */
    public void setTranslatedDisplayName(String translatedDisplayName) {
        this.translatedDisplayName = translatedDisplayName;
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
     * @return The sha1
     */
    public String getSha1() {
        return sha1;
    }

    /**
     * @param sha1 The sha1
     */
    public void setSha1(String sha1) {
        this.sha1 = sha1;
    }

    /**
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The prerequisites
     */
    public List<String> getPrerequisites() {
        return prerequisites;
    }

    /**
     * @param prerequisites The prerequisites
     */
    public void setPrerequisites(List<String> prerequisites) {
        this.prerequisites = prerequisites;
    }

    /**
     * @return The assessmentItemTags
     */
    public List<String> getAssessmentItemTags() {
        return assessmentItemTags;
    }

    /**
     * @param assessmentItemTags The assessment_item_tags
     */
    public void setAssessmentItemTags(List<String> assessmentItemTags) {
        this.assessmentItemTags = assessmentItemTags;
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
     * @return The secondsPerFastProblem
     */
    public int getSecondsPerFastProblem() {
        return secondsPerFastProblem;
    }

    /**
     * @param secondsPerFastProblem The seconds_per_fast_problem
     */
    public void setSecondsPerFastProblem(int secondsPerFastProblem) {
        this.secondsPerFastProblem = secondsPerFastProblem;
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
     * @return The tutorialOnly
     */
    public boolean isTutorialOnly() {
        return tutorialOnly;
    }

    /**
     * @param tutorialOnly The tutorial_only
     */
    public void setTutorialOnly(boolean tutorialOnly) {
        this.tutorialOnly = tutorialOnly;
    }

    /**
     * @return The currentRevisionKey
     */
    public String getCurrentRevisionKey() {
        return currentRevisionKey;
    }

    /**
     * @param currentRevisionKey The current_revision_key
     */
    public void setCurrentRevisionKey(String currentRevisionKey) {
        this.currentRevisionKey = currentRevisionKey;
    }

    /**
     * @return The imageUrl256
     */
    public String getImageUrl256() {
        return imageUrl256;
    }

    /**
     * @param imageUrl256 The image_url_256
     */
    public void setImageUrl256(String imageUrl256) {
        this.imageUrl256 = imageUrl256;
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

}