package com.subhrajyoti.chuckdagger.dagger.component;


import com.subhrajyoti.chuckdagger.MainActivity;
import com.subhrajyoti.chuckdagger.dagger.module.ActivityModule;
import com.subhrajyoti.chuckdagger.dagger.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(modules = {ActivityModule.class}, dependencies = {NetworkComponent.class})
public interface ActivityComponent {
    void inject(MainActivity mainActivity);
}
