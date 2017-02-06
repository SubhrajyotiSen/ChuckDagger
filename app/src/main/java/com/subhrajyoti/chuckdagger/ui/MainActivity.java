package com.subhrajyoti.chuckdagger.ui;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.subhrajyoti.chuckdagger.MyApplication;
import com.subhrajyoti.chuckdagger.R;
import com.subhrajyoti.chuckdagger.model.JokeModel;
import com.subhrajyoti.chuckdagger.retrofit.RestAPI;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    @Inject
    Retrofit retrofit;
    private TextView textView;
    private ProgressBar progressBar;
    private FloatingActionButton floatingActionButton;
    private Toolbar toolbar;
    private Call<JokeModel> joke;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((MyApplication) getApplication()).getNetComponent().inject(this);

        textView = (TextView) findViewById(R.id.textView);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newJoke();
            }
        });

        newJoke();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id) {
            case R.id.settings:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(getString(R.string.app_name));
                builder.setMessage(R.string.app_description);
                builder.setPositiveButton(getString(R.string.dialog_positive),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
                break;

        }

        return super.onOptionsItemSelected(item);
    }

    public void newJoke(){
        joke = retrofit.create(RestAPI.class).getJoke();
        textView.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);
        joke.enqueue(new Callback<JokeModel>() {
            @Override
            public void onResponse(Call<JokeModel> call, Response<JokeModel> response) {
                String joke = response.body().getJoke();
                progressBar.setVisibility(View.INVISIBLE);
                textView.setVisibility(View.VISIBLE);
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
