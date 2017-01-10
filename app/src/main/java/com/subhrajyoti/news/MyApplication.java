package com.subhrajyoti.news;

import android.app.Application;

import com.subhrajyoti.news.dagger.component.DaggerNetComponent;
import com.subhrajyoti.news.dagger.component.NetComponent;
import com.subhrajyoti.news.dagger.module.AppModule;
import com.subhrajyoti.news.dagger.module.NetModule;

public class MyApplication extends Application {

    private NetComponent mNetComponent;
    private final String URL = "http://api.icndb.com/";

    @Override
    public void onCreate() {
        super.onCreate();

        mNetComponent = DaggerNetComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule(URL))
                .build();
    }

    public NetComponent getNetComponent() {
        return mNetComponent;
    }
}
