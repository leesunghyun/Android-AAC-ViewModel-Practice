package com.maumqmaum.androidnewarchpractice;

import android.arch.lifecycle.LifecycleActivity;
import android.os.Bundle;

public class MainActivity extends LifecycleActivity {

    private NavigationController navigationController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigationController = new NavigationController(this);
        if (savedInstanceState == null) {
            navigationController.navigateToMain();
        }
    }

    public NavigationController getNavigationController() {
        return navigationController;
    }
}
