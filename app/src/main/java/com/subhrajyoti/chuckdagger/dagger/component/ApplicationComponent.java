package com.subhrajyoti.chuckdagger.dagger.component;

import android.app.Application;

import com.subhrajyoti.chuckdagger.dagger.module.AppModule;
import com.subhrajyoti.chuckdagger.dagger.scope.ApplicationScope;

import dagger.Component;

@ApplicationScope
@Component(modules = {AppModule.class})
public interface ApplicationComponent {
    Application application();
}
