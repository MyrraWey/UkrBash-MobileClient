package com.zinitsolutions.test.testapplication.posts;

/**
 * Created by MyrraWey on 25.04.2016.
 */
public class LoadingPost implements IPost {

    @Override
    public int getPostType() {
        return PostType.LOADING_POST;
    }

}
