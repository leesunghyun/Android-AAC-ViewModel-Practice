package com.maumqmaum.androidnewarchpractice.di.module;

import com.maumqmaum.androidnewarchpractice.MainActivity;
import com.maumqmaum.androidnewarchpractice.NavigationController;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    final MainActivity activity;

    public ActivityModule(MainActivity activity) {
        this.activity = activity;
    }

    @Provides
    public NavigationController provideNavigationController() {
        return new NavigationController(activity);
    }
}
