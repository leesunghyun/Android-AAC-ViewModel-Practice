package com.maumqmaum.androidnewarchpractice.di.component;

import com.maumqmaum.androidnewarchpractice.ui.fragment.ArticleListFragment;

import dagger.Subcomponent;

@Subcomponent
public interface FragmentComponent {
    void inject(ArticleListFragment fragment);
}
