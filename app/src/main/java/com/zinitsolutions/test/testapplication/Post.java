package com.zinitsolutions.test.testapplication;

import java.util.Date;

/**
 * Created by dmitrij on 4/19/16.
 */
public class Post {
    private String mTitle;
    private Date mDate;

    public Post(String mTitle) {
        this.mTitle = mTitle;
        this.mDate = new Date();
    }

    public String getTitle() {
        return mTitle;
    }

    public Date getDate() {
        return mDate;
    }
}
