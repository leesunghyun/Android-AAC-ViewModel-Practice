package com.maumqmaum.androidnewarchpractice.di.module;

import com.maumqmaum.androidnewarchpractice.repository.ArticleRepository;
import com.maumqmaum.androidnewarchpractice.repository.ArticleRepositoryImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Singleton
@Module
public class RepositoryModule {

    @Provides
    public ArticleRepository provideArticleRepository() {
        return new ArticleRepositoryImpl();
    }
}
