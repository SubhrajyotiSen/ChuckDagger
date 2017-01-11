package com.subhrajyoti.news.retrofit;

import com.subhrajyoti.news.model.JokeModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RestAPI {

    @GET("jokes/random")
    Call<JokeModel> getJoke();
}