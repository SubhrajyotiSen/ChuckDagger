package com.subhrajyoti.chuckdagger.mvp.presenter;

import android.util.Log;
import android.view.View;

import com.subhrajyoti.chuckdagger.mvp.model.JokeModel;
import com.subhrajyoti.chuckdagger.mvp.view.MainView;
import com.subhrajyoti.chuckdagger.retrofit.RestAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainPresenter {

    private MainView mainView;

    public MainPresenter(MainView mainView) {
        this.mainView = mainView;
    }

    public void newJoke(Call<JokeModel> joke) {
        mainView.setTextViewVisibility(View.INVISIBLE);
        mainView.setProgressBarVisibility(View.VISIBLE);
        joke.enqueue(new Callback<JokeModel>() {
            @Override
            public void onResponse(Call<JokeModel> call, Response<JokeModel> response) {
                String joke = response.body().getJoke();
                mainView.setProgressBarVisibility(View.INVISIBLE);
                mainView.setTextViewVisibility(View.VISIBLE);
                mainView.setTextViewText(joke.replaceAll("&quot;", "\""));
                Log.d("TAG",response.body().getJoke());

            }

            @Override
            public void onFailure(Call<JokeModel> call, Throwable t) {
                mainView.setTextViewText(t.toString());
            }
        });

    }
}
