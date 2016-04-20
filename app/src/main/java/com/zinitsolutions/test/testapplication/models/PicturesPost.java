package com.zinitsolutions.test.testapplication.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by dmitrij on 4/19/16.
 */
public class PicturesPost implements Serializable {
    /**
     * ID картинки
     */
    @SerializedName("id")
    private Integer mId;

    /**
     * 1 – неопублікована, 2 — опублікована, 0 — видалена
     */
    @SerializedName("status")
    private Integer mStatus;

    /**
     * picture
     */
    @SerializedName("type")
    private String mType;

    /**
     * дата додавання, unix time
     */
    @SerializedName("add_date")
    private String mAddDate;

    /**
     * дата публікації, unix time, 0 якщо status=1
     */
    @SerializedName("pub_date")
    private String mPubDate;

    /**
     * login або анонім
     */
    @SerializedName("author")
    private String mAuthor;

    /**
     * ID автора, 0 якщо анонім
     */
    @SerializedName("author_id")
    private Integer mAuthorId;

    /**
     * картинка 400х400
     */
    @SerializedName("image")
    private String mImage;

    /**
     * картинка 150х150
     */
    @SerializedName("thumbnail")
    private String mThumbnail;

    /**
     * навзва картинки
     */
    @SerializedName("title")
    private String mTitle;

    private Boolean mViewed;

    /**
     * рейтинг
     */
    @SerializedName("rating")
    private Integer mRating;

    public PicturesPost() {
        this.mViewed = false;
    }

    public Integer getId() {
        return mId;
    }

    public Integer getStatus() {
        return mStatus;
    }

    public String getType() {
        return mType;
    }

    public String getAddDate() {
        return mAddDate;
    }

    public String getPubDate() {
        return mPubDate;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public Integer getAuthorId() {
        return mAuthorId;
    }

    public String getImage() {
        return mImage;
    }

    public String getThumbnail() {
        return mThumbnail;
    }

    public String getTitle() {
        return mTitle;
    }

    public Integer getRating() {
        return mRating;
    }

    public Boolean getViewed() {
        return mViewed;
    }

    public void setViewed(Boolean viewed) {
        mViewed = viewed;
    }
}
