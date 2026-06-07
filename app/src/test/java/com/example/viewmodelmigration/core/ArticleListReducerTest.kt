package com.example.viewmodelmigration.core

import org.junit.Assert.assertEquals
import org.junit.Test

class ArticleListReducerTest {

    @Test
    fun selectArticle_updatesSelectedArticleId() {
        val oldState = ArticleListUiState(
            articles = listOf(
                Article(id = "1", title = "One", body = "Body 1"),
                Article(id = "2", title = "Two", body = "Body 2")
            ),
            selectedArticleId = null
        )

        val newState = ArticleListReducer.reduce(
            state = oldState,
            action = ArticleListAction.SelectArticle("2")
        )

        assertEquals("2", newState.selectedArticleId)
        assertEquals(oldState.articles.size, newState.articles.size)
    }

    @Test
    fun selectArticle_invalidId_clearsSelectionForInvalidStateSafety() {
        val oldState = ArticleListUiState(
            articles = listOf(
                Article(id = "1", title = "One", body = "Body 1"),
                Article(id = "2", title = "Two", body = "Body 2")
            ),
            selectedArticleId = "1"
        )

        val newState = ArticleListReducer.reduce(
            state = oldState,
            action = ArticleListAction.SelectArticle("missing-id")
        )

        assertEquals(null, newState.selectedArticleId)
        assertEquals(oldState.articles, newState.articles)
    }

    @Test
    fun updateArticle_updatesArticleInList() {
        val oldArticle = Article(
            id = "1",
            title = "Old title",
            body = "Old body"
        )

        val updatedArticle = Article(
            id = "1",
            title = "New title",
            body = "New body"
        )

        val oldState = ArticleListUiState(
            articles = listOf(oldArticle)
        )

        val newState = ArticleListReducer.reduce(
            state = oldState,
            action = ArticleListAction.UpdateArticle(updatedArticle)
        )

        assertEquals("New title", newState.articles.first().title)
        assertEquals("New body", newState.articles.first().body)
    }

    @Test
    fun deleteArticle_removesArticleFromList() {
        val oldState = ArticleListUiState(
            articles = listOf(
                Article(id = "1", title = "Keep", body = "Body 1"),
                Article(id = "2", title = "Remove", body = "Body 2")
            ),
            selectedArticleId = "2"
        )

        val newState = ArticleListReducer.reduce(
            state = oldState,
            action = ArticleListAction.DeleteArticle("2")
        )

        assertEquals(1, newState.articles.size)
        assertEquals(false, newState.articles.any { it.id == "2" })
    }

    @Test
    fun deleteSelectedArticle_clearsSelectedArticleId() {
        val oldState = ArticleListUiState(
            articles = listOf(
                Article(id = "1", title = "Keep", body = "Body 1"),
                Article(id = "2", title = "Selected", body = "Body 2")
            ),
            selectedArticleId = "2"
        )

        val newState = ArticleListReducer.reduce(
            state = oldState,
            action = ArticleListAction.DeleteArticle("2")
        )

        assertEquals(null, newState.selectedArticleId)
        assertEquals(1, newState.articles.size)
    }

    @Test
    fun clearSelection_setsSelectedArticleIdNull() {
        val oldState = ArticleListUiState(
            articles = listOf(
                Article(id = "1", title = "Keep", body = "Body 1")
            ),
            selectedArticleId = "1"
        )

        val newState = ArticleListReducer.reduce(
            state = oldState,
            action = ArticleListAction.ClearSelection
        )

        assertEquals(null, newState.selectedArticleId)
    }
}
