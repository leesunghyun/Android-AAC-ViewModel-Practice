package com.maumqmaum.androidnewarchpractice.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.maumqmaum.androidnewarchpractice.App;
import com.maumqmaum.androidnewarchpractice.model.Article;
import com.maumqmaum.androidnewarchpractice.repository.ArticleRepository;

import java.util.List;

import javax.inject.Inject;

public class ArticleListViewModel extends ViewModel {

    private MutableLiveData<List<MutableLiveData<Article>>> articleList = new MutableLiveData<>();

    @Inject
    ArticleRepository articleRepository;

    public ArticleListViewModel() {
        App.getInstance().getComponent().plus().inject(this);
        this.articleList.setValue(articleRepository.getArticleList());
    }

    public MutableLiveData<List<MutableLiveData<Article>>> getArticleList() {
        return articleList;
    }
}
