package com.example.viewmodelmigration.core

sealed interface ArticleListAction {
    data class SelectArticle(val articleId: String) : ArticleListAction
    data class UpdateArticle(val article: Article) : ArticleListAction
    data class DeleteArticle(val articleId: String) : ArticleListAction
    data object ClearSelection : ArticleListAction
}
