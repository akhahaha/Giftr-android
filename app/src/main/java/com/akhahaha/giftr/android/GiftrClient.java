package com.akhahaha.giftr.android;

import com.akhahaha.giftr.android.model.Match;
import com.akhahaha.giftr.android.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Giftr service client
 * Created by Alan on 5/25/2016.
 */
public final class GiftrClient {
    private static final String GIFTR_BASE_URL =
            "http://ec2-52-37-240-117.us-west-2.compute.amazonaws.com:8080";

    private static final Retrofit restAdapter = new Retrofit.Builder()
            .baseUrl(GIFTR_BASE_URL)
            .addConverterFactory(JacksonConverterFactory.create())
            .build();

    private static final GiftrService service = restAdapter.create(GiftrService.class);

    public static GiftrService getService() {
        return service;
    }

    public interface GiftrService {
        @GET("/users")
        Call<List<User>> getAllUsers();

        @GET("/users/{userID}")
        Call<User> getUser(@Path("userID") Integer userID);

        @GET("/users/{userID}/matches")
        Call<List<Match>> getUserMatches(@Path("userID") Integer userID);
    }
}
