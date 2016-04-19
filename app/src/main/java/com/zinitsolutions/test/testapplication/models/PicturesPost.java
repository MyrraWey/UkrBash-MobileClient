package com.zinitsolutions.test.testapplication.models;

import org.json.JSONObject;

/**
 * Created by dmitrij on 4/19/16.
 */
public class PicturesPost implements Post{
    /**
     * ID картинки
     */
    private Integer mId;

    /**
     * 1 – неопублікована, 2 — опублікована, 0 — видалена
     */
    private Integer mStatus;

    /**
     * picture
     */
    private String mType;

    /**
     * дата додавання, unix time
     */
    private String mAddDate;

    /**
     * дата публікації, unix time, 0 якщо status=1
     */
    private String mPubDate;

    /**
     * login або анонім
     */
    private String mAuthor;

    /**
     * ID автора, 0 якщо анонім
     */
    private Integer mAuthorId;

    /**
     * картинка 400х400
     */
    private String mImage;

    /**
     * картинка 150х150
     */
    private String mThumbnail;

    /**
     * навзва картинки
     */
    private String mTitle;

    /**
     * рейтинг
     */
    private Integer mRating;

    public static PicturesPost CreateFromJSON(JSONObject obj) {
        PicturesPost picturesPost = new PicturesPost();

        return picturesPost;
    }

    @Override
    public String getTitle() {
        return this.mTitle;
    }

    @Override
    public String getThumbnail() {
        return this.mThumbnail;
    }
}
