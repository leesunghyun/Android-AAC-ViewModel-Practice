package com.maumqmaum.androidnewarchpractice.repository;


import android.arch.lifecycle.MutableLiveData;

import com.maumqmaum.androidnewarchpractice.model.Article;

import java.util.ArrayList;
import java.util.List;

public class ArticleRepositoryImpl implements ArticleRepository {

    public List<MutableLiveData<Article>> getArticleList() {
        return createMockArticle();
    }

    private List<MutableLiveData<Article>> createMockArticle() {
        ArrayList<MutableLiveData<Article>> articleList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            MutableLiveData<Article> article = new MutableLiveData<>();
            article.setValue(new Article("title" + i, "description" + i, "author" + i, false));
            articleList.add(article);
        }
        return articleList;
    }
}
