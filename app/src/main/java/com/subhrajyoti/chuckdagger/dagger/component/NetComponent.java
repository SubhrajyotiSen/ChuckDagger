package com.subhrajyoti.chuckdagger.dagger.component;

import com.subhrajyoti.chuckdagger.ui.MainActivity;
import com.subhrajyoti.chuckdagger.dagger.module.AppModule;
import com.subhrajyoti.chuckdagger.dagger.module.NetModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface NetComponent {
    void inject(MainActivity activity);
}