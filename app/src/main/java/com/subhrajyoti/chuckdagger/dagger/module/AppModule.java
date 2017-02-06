package com.subhrajyoti.chuckdagger.dagger.module;

import android.app.Application;

import com.subhrajyoti.chuckdagger.dagger.scope.ApplicationScope;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private Application application;

    public AppModule(Application application){
        this.application = application;
    }

    @ApplicationScope
    @Provides
    Application provideApplication(){
        return application;
    }
}
