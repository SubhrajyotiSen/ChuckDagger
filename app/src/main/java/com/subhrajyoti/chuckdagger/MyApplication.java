package com.subhrajyoti.chuckdagger;

import android.app.Activity;
import android.app.Application;

import com.subhrajyoti.chuckdagger.dagger.component.ApplicationComponent;
import com.subhrajyoti.chuckdagger.dagger.component.DaggerApplicationComponent;
import com.subhrajyoti.chuckdagger.dagger.module.AppModule;

public class MyApplication extends Application {

    private ApplicationComponent applicationComponent;

    public static MyApplication get(Activity activity) {
        return (MyApplication) activity.getApplication();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent.builder()
                .appModule(new AppModule(this))
                .build();

    }

    public ApplicationComponent getApplicationComponent(){
        return applicationComponent;
    }


}