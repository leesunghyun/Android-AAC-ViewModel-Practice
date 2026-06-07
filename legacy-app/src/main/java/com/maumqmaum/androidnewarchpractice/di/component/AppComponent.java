package com.maumqmaum.androidnewarchpractice.di.component;


import com.maumqmaum.androidnewarchpractice.App;
import com.maumqmaum.androidnewarchpractice.di.module.ActivityModule;
import com.maumqmaum.androidnewarchpractice.di.module.AppModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    void inject(App app);

    ViewModelComponent plus();

    ActivityComponent plus(ActivityModule module);
}
