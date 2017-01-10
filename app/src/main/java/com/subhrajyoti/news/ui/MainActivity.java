package com.subhrajyoti.news.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.subhrajyoti.news.JokeModel;
import com.subhrajyoti.news.MyApplication;
import com.subhrajyoti.news.R;
import com.subhrajyoti.news.RestAPI;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    @Inject
    Retrofit retrofit;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((MyApplication) getApplication()).getNetComponent().inject(this);

        textView = (TextView) findViewById(R.id.textView);
        Call<JokeModel> joke = retrofit.create(RestAPI.class).getJoke();

        joke.enqueue(new Callback<JokeModel>() {
            @Override
            public void onResponse(Call<JokeModel> call, Response<JokeModel> response) {
                String joke = response.body().getJoke();
                textView.setText(joke.replaceAll("&quot;", "\""));
                Log.d("TAG",response.body().getJoke());

            }

            @Override
            public void onFailure(Call<JokeModel> call, Throwable t) {
                textView.setText(t.toString());
            }
        });
    }
}
