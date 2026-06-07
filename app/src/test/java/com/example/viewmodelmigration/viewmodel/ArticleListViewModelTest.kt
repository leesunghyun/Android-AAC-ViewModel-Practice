package com.example.viewmodelmigration.viewmodel

import com.example.viewmodelmigration.core.Article
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Test

class ArticleListViewModelTest {

    @Test
    fun selectArticle_updatesSelectedArticleId() {
        val viewModel = ArticleListViewModel()

        viewModel.selectArticle("2")

        assertEquals("2", viewModel.uiState.value.selectedArticleId)
    }

    @Test
    fun updateArticle_updatesUiState() {
        val viewModel = ArticleListViewModel()

        val updatedArticle = Article(
            id = "1",
            title = "Updated title",
            body = "Updated body"
        )

        viewModel.updateArticle(updatedArticle)

        val article = viewModel.uiState.value.articles.first {
            it.id == "1"
        }

        assertEquals("Updated title", article.title)
        assertEquals("Updated body", article.body)
    }

    @Test
    fun deleteArticle_removesArticleFromUiState() {
        val viewModel = ArticleListViewModel()

        viewModel.deleteArticle("1")

        assertFalse(
            viewModel.uiState.value.articles.any {
                it.id == "1"
            }
        )
    }

    @Test
    fun deleteSelectedArticle_clearsSelectedArticleId() {
        val viewModel = ArticleListViewModel()

        viewModel.selectArticle("1")
        viewModel.deleteArticle("1")

        assertNull(viewModel.uiState.value.selectedArticleId)
    }
}
