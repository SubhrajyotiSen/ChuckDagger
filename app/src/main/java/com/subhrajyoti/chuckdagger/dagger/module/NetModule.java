package com.subhrajyoti.chuckdagger.dagger.module;

import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.subhrajyoti.chuckdagger.dagger.scope.ActivityScope;
import com.subhrajyoti.chuckdagger.mvp.model.JokeModel;
import com.subhrajyoti.chuckdagger.retrofit.MyDeserializer;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetModule {
    private String mBaseUrl;
    private Application application;

    public NetModule(String mBaseUrl, Application application) {
        this.mBaseUrl = mBaseUrl;
        this.application = application;
    }


    @Provides
    @ActivityScope
    Cache provideHttpCache(Application application) {
        int cacheSize = 10 * 1024 * 1024;
        return new Cache(application.getCacheDir(), cacheSize);
    }

    @Provides
    @ActivityScope
    Gson provideGson() {
        return new GsonBuilder()
                .registerTypeAdapter(JokeModel.class, new MyDeserializer())
                .create();
    }

    @Provides
    @ActivityScope
    OkHttpClient provideOkhttpClient(Cache cache) {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.cache(cache);
        return client.build();
    }

    @Provides
    @ActivityScope
    Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient, RxJava2CallAdapterFactory rxJava2CallAdapterFactory) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(mBaseUrl)
                .client(okHttpClient)
                .addCallAdapterFactory(rxJava2CallAdapterFactory)
                .build();
    }

    @Provides
    @ActivityScope
    Application application() {
        return application;
    }

    @Provides
    @ActivityScope
    RxJava2CallAdapterFactory rxJava2CallAdapterFactory(){
        return RxJava2CallAdapterFactory.create();
    }


}