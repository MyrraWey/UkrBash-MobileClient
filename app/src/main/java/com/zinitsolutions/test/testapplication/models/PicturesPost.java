package com.zinitsolutions.test.testapplication.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by dmitrij on 4/19/16.
 */
public class PicturesPost implements Post {
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

    /**
     * рейтинг
     */
    @SerializedName("rating")
    private Integer mRating;

    @Override
    public String getTitle() {
        return this.mTitle;
    }

    @Override
    public String getThumbnail() {
        return this.mThumbnail;
    }
}
