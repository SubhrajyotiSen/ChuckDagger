package com.subhrajyoti.chuckdagger.retrofit;

import com.subhrajyoti.chuckdagger.model.JokeModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RestAPI {

    @GET("jokes/random")
    Call<JokeModel> getJoke();
}