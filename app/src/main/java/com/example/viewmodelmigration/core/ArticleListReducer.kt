package com.example.viewmodelmigration.core

object ArticleListReducer {
    fun reduce(
        state: ArticleListUiState,
        action: ArticleListAction
    ): ArticleListUiState {
        return when (action) {
            is ArticleListAction.SelectArticle -> {
                state.copy(
                    selectedArticleId = action.articleId
                )
            }

            is ArticleListAction.UpdateArticle -> {
                state.copy(
                    articles = state.articles.map { article ->
                        if (article.id == action.article.id) {
                            action.article
                        } else {
                            article
                        }
                    }
                )
            }

            is ArticleListAction.DeleteArticle -> {
                state.copy(
                    articles = state.articles.filterNot { article ->
                        article.id == action.articleId
                    },
                    selectedArticleId = if (state.selectedArticleId == action.articleId) {
                        null
                    } else {
                        state.selectedArticleId
                    }
                )
            }

            is ArticleListAction.ClearSelection -> {
                state.copy(
                    selectedArticleId = null
                )
            }
        }
    }
}
