package com.maumqmaum.androidnewarchpractice;

import android.app.Application;

import com.maumqmaum.androidnewarchpractice.di.component.AppComponent;
import com.maumqmaum.androidnewarchpractice.di.component.DaggerAppComponent;
import com.maumqmaum.androidnewarchpractice.di.module.AppModule;

public class App extends Application {

    public static App app;

    private AppComponent appComponent;

    public static App getInstance() {
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getComponent() {
        return appComponent;
    }
}
