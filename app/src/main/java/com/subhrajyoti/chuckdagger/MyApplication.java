package com.subhrajyoti.chuckdagger;

import android.app.Activity;
import android.app.Application;

import com.subhrajyoti.chuckdagger.dagger.component.DaggerNetworkComponent;
import com.subhrajyoti.chuckdagger.dagger.component.NetworkComponent;
import com.subhrajyoti.chuckdagger.dagger.module.ApplicationModule;
import com.subhrajyoti.chuckdagger.dagger.module.NetworkModule;

@SuppressWarnings("FieldCanBeLocal")
public class MyApplication extends Application {

    private NetworkComponent networkComponent;
    private final String URL = "http://api.icndb.com/";

    public static MyApplication get(Activity activity) {
        return (MyApplication) activity.getApplication();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        networkComponent = DaggerNetworkComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .networkModule(new NetworkModule(URL))
                .build();
    }



    public NetworkComponent getNetworkComponent() {
        return networkComponent;
    }
}
