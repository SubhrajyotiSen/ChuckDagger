package com.subhrajyoti.chuckdagger.dagger.module;


import com.subhrajyoti.chuckdagger.dagger.scope.ActivityScope;
import com.subhrajyoti.chuckdagger.mvp.view.MainView;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    private MainView mainView;

    public ActivityModule(MainView mainView) {
        this.mainView = mainView;
    }

    @ActivityScope
    @Provides
    MainView mainView() {
        return mainView;
    }
}
