package com.maumqmaum.androidnewarchpractice;

import android.app.Application;

public class App extends Application {

    public static App app;

    public static App getInstance() {
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
    }
}
