package com.subhrajyoti.chuckdagger.retrofit;

import com.subhrajyoti.chuckdagger.mvp.model.JokeModel;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface RestAPI {

    @GET("jokes/random")
    Observable<JokeModel> getJoke();
}