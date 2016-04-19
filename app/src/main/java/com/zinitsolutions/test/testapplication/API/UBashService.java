package com.zinitsolutions.test.testapplication.API;

import com.zinitsolutions.test.testapplication.models.PicturesPost;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by MyrraWey on 19.04.2016.
 */
public interface UBashService {
    @GET("pictures.getRandom.json")
    Call<List<PicturesPost>> getRandomPictures(@Query("client") String api_key);
}
