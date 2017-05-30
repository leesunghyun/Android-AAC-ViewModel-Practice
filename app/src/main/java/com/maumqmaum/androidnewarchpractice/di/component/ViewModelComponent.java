package com.maumqmaum.androidnewarchpractice.di.component;

import com.maumqmaum.androidnewarchpractice.di.ActivityScope;
import com.maumqmaum.androidnewarchpractice.di.module.RepositoryModule;
import com.maumqmaum.androidnewarchpractice.viewmodel.ArticleListViewModel;

import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = {RepositoryModule.class})
public interface ViewModelComponent {
    void inject(ArticleListViewModel viewModel);
}
