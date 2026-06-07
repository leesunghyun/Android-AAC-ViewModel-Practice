package com.example.viewmodelmigration.viewmodel

import androidx.lifecycle.ViewModel
import com.example.viewmodelmigration.core.Article
import com.example.viewmodelmigration.core.ArticleListAction
import com.example.viewmodelmigration.core.ArticleListReducer
import com.example.viewmodelmigration.core.ArticleListUiState
import com.example.viewmodelmigration.core.SampleArticles
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ArticleListViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(
        ArticleListUiState(
            articles = SampleArticles.create()
        )
    )

    val uiState: StateFlow<ArticleListUiState> = _uiState.asStateFlow()

    fun selectArticle(articleId: String) {
        dispatch(ArticleListAction.SelectArticle(articleId))
    }

    fun updateArticle(article: Article) {
        dispatch(ArticleListAction.UpdateArticle(article))
    }

    fun deleteArticle(articleId: String) {
        dispatch(ArticleListAction.DeleteArticle(articleId))
    }

    fun clearSelection() {
        dispatch(ArticleListAction.ClearSelection)
    }

    private fun dispatch(action: ArticleListAction) {
        _uiState.update { oldState ->
            ArticleListReducer.reduce(
                state = oldState,
                action = action
            )
        }
    }
}
