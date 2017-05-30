package com.maumqmaum.androidnewarchpractice.repository;

import android.arch.lifecycle.MutableLiveData;

import com.maumqmaum.androidnewarchpractice.model.Article;

import java.util.List;

public interface ArticleRepository {
    List<MutableLiveData<Article>> getArticleList();
}
