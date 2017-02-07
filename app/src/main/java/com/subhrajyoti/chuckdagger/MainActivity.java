package com.subhrajyoti.chuckdagger;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.subhrajyoti.chuckdagger.dagger.component.DaggerNetworkComponent;
import com.subhrajyoti.chuckdagger.dagger.component.NetworkComponent;
import com.subhrajyoti.chuckdagger.dagger.module.NetModule;
import com.subhrajyoti.chuckdagger.mvp.presenter.MainPresenter;
import com.subhrajyoti.chuckdagger.mvp.view.MainView;
import com.subhrajyoti.chuckdagger.retrofit.RestAPI;

import javax.inject.Inject;

import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements MainView {

    @Inject
    Retrofit retrofit;
    TextView textView;
    ProgressBar progressBar;
    FloatingActionButton floatingActionButton;
    Toolbar toolbar;
    private final String URL = "http://api.icndb.com/";
    private MainPresenter mainPresenter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NetworkComponent networkComponent = DaggerNetworkComponent.builder()
                .netModule(new NetModule(URL, this.getApplication()))
                .build();
        networkComponent.injectMainActivity(this);

        mainPresenter = new MainPresenter(this);

        textView = (TextView) findViewById(R.id.textView);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mainPresenter.newJoke(retrofit.create(RestAPI.class).getJoke());
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainPresenter.newJoke(retrofit.create(RestAPI.class).getJoke());
            }
        });


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

    @Override
    public void setTextViewVisibility(int visibility) {
        textView.setVisibility(visibility);
    }

    @Override
    public void setProgressBarVisibility(int visibility) {
        progressBar.setVisibility(visibility);
    }

    @Override
    public void setTextViewText(String string) {
        textView.setText(string);
    }

}
