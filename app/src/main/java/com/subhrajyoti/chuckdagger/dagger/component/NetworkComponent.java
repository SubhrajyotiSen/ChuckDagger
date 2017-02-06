package com.subhrajyoti.chuckdagger.dagger.component;

import com.subhrajyoti.chuckdagger.dagger.module.NetModule;
import com.subhrajyoti.chuckdagger.dagger.scope.ActivityScope;
import com.subhrajyoti.chuckdagger.ui.MainActivity;

import dagger.Component;

@ActivityScope
@Component(modules = {NetModule.class}, dependencies = {ApplicationComponent.class})
public interface NetworkComponent {
    void inject(MainActivity activity);
}