package com.subhrajyoti.chuckdagger;

import android.app.Application;

import com.subhrajyoti.chuckdagger.dagger.component.DaggerNetComponent;
import com.subhrajyoti.chuckdagger.dagger.component.NetComponent;
import com.subhrajyoti.chuckdagger.dagger.module.AppModule;
import com.subhrajyoti.chuckdagger.dagger.module.NetModule;

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
