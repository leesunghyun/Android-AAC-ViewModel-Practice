package com.maumqmaum.androidnewarchpractice.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.maumqmaum.androidnewarchpractice.model.Article;
import com.maumqmaum.androidnewarchpractice.repository.ArticleRepository;

import java.util.List;

public class ArticleListViewModel extends ViewModel {

    private MutableLiveData<List<MutableLiveData<Article>>> articleList = new MutableLiveData<>();

    //TODO : Use dagger.
    private ArticleRepository articleRepository;

    public void setArticleRepository(ArticleRepository articleRepository) {
        if (this.articleRepository == null) {
            this.articleRepository = articleRepository;
            this.articleList.setValue(articleRepository.getArticleList());
        }
    }

    public MutableLiveData<List<MutableLiveData<Article>>> getArticleList() {
        return articleList;
    }
}
