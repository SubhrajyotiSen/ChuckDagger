package com.subhrajyoti.chuckdagger.dagger.component;

import com.subhrajyoti.chuckdagger.dagger.module.ApplicationModule;
import com.subhrajyoti.chuckdagger.dagger.module.NetworkModule;
import com.subhrajyoti.chuckdagger.dagger.scope.CustomScope;

import dagger.Component;
import retrofit2.Retrofit;

@CustomScope
@Component(modules = {NetworkModule.class, ApplicationModule.class})
public interface NetworkComponent {

    Retrofit retrofit();
}