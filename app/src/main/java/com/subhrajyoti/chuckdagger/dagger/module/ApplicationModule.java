package com.subhrajyoti.chuckdagger.dagger.module;

import android.app.Application;

import com.subhrajyoti.chuckdagger.dagger.scope.CustomScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private Application application;

    public ApplicationModule(Application application){
        this.application = application;
    }

    @Provides
    @CustomScope
    Application application(){
        return application;
    }
}
