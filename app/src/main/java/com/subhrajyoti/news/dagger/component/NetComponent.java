package com.subhrajyoti.news.dagger.component;

import com.subhrajyoti.news.ui.MainActivity;
import com.subhrajyoti.news.dagger.module.AppModule;
import com.subhrajyoti.news.dagger.module.NetModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface NetComponent {
    void inject(MainActivity activity);
}