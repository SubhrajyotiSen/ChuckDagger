package com.subhrajyoti.chuckdagger.mvp.presenter;

import android.util.Log;
import android.view.View;

import com.subhrajyoti.chuckdagger.mvp.model.JokeModel;
import com.subhrajyoti.chuckdagger.mvp.view.MainView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenter {

    private MainView mainView;

    public MainPresenter(MainView mainView) {
        this.mainView = mainView;
    }

    public void newJoke(final Call<JokeModel> joke) {
        mainView.setTextViewVisibility(View.INVISIBLE);
        mainView.setProgressBarVisibility(View.VISIBLE);
        joke.enqueue(new Callback<JokeModel>() {
            @Override
            public void onResponse(Call<JokeModel> call, Response<JokeModel> response) {
                String jokeString = response.body().getJoke();
                mainView.setProgressBarVisibility(View.INVISIBLE);
                mainView.setTextViewVisibility(View.VISIBLE);
                mainView.setTextViewText(jokeString.replaceAll("&quot;", "\""));
                Log.d("TAG",response.body().getJoke());
                joke.cancel();

            }

            @Override
            public void onFailure(Call<JokeModel> call, Throwable t) {
                mainView.setTextViewText(t.toString());
                joke.cancel();
            }
        });


    }
}
