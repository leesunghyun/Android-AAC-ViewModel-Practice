package com.maumqmaum.androidnewarchpractice.di.component;

import com.maumqmaum.androidnewarchpractice.MainActivity;
import com.maumqmaum.androidnewarchpractice.di.module.ActivityModule;
import com.maumqmaum.androidnewarchpractice.di.ActivityScope;

import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = {ActivityModule.class})
public interface ActivityComponent {

    void inject(MainActivity activity);

    FragmentComponent plus();
}
