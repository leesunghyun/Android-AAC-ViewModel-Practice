package com.maumqmaum.androidnewarchpractice;

import android.arch.lifecycle.LifecycleActivity;
import android.os.Bundle;

import com.maumqmaum.androidnewarchpractice.di.component.ActivityComponent;
import com.maumqmaum.androidnewarchpractice.di.module.ActivityModule;

import javax.inject.Inject;

public class MainActivity extends LifecycleActivity {

    @Inject
    public NavigationController navigationController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getComponent().inject(this);
        if (savedInstanceState == null) {
            navigationController.navigateToMain();
        }
    }

    public ActivityComponent getComponent() {
        return App.getInstance().getComponent().plus(new ActivityModule(this));
    }
}
