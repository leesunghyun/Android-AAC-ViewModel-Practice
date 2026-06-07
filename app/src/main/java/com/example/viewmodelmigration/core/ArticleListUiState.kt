package com.example.viewmodelmigration.core

data class ArticleListUiState(
    val articles: List<Article> = emptyList(),
    val selectedArticleId: String? = null
)
