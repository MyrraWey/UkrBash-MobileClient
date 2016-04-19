package com.zinitsolutions.test.testapplication.API;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by MyrraWey on 19.04.2016.
 */
public class ApiFactory {
    //TODO replace with proper method
    private static final String API_ENDPOINT = "https://api.ukrbash.org/1/";

    public static UBashService getUBashService() {
        return getRetrofit().create(UBashService.class);
    }

    private static Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(API_ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
