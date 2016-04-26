package com.zinitsolutions.test.testapplication.API;

import com.zinitsolutions.test.testapplication.posts.PicturesPost;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by MyrraWey on 19.04.2016.
 */
public interface UBashService {

    @GET("pictures.getPublished.json")
    Call<List<PicturesPost>> getPublishedPictures(@Query("limit") Integer count);

    @GET("pictures.getPublished.json")
    Call<List<PicturesPost>> getPublishedPictures(@Query("limit") Integer count, @Query("start") Integer offset);

}
