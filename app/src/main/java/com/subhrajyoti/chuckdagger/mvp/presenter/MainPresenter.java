package com.subhrajyoti.chuckdagger.mvp.presenter;

import android.view.View;

import com.subhrajyoti.chuckdagger.mvp.model.JokeModel;
import com.subhrajyoti.chuckdagger.mvp.view.MainView;
import com.subhrajyoti.chuckdagger.retrofit.RestAPI;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class MainPresenter {

    private MainView mainView;

    public MainPresenter(MainView mainView) {
        this.mainView = mainView;
    }

    public void newJoke(final Retrofit retrofit) {
        mainView.setTextViewVisibility(View.INVISIBLE);
        mainView.setProgressBarVisibility(View.VISIBLE);
        retrofit.create(RestAPI.class).getJoke()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<JokeModel>() {
                    @Override
                    public void accept(JokeModel jokeModel) throws Exception {
                        mainView.setProgressBarVisibility(View.INVISIBLE);
                        mainView.setTextViewVisibility(View.VISIBLE);
                        mainView.setTextViewText(jokeModel.getJoke().replaceAll("&quot;", "\""));

                    }
                });


    }
}
