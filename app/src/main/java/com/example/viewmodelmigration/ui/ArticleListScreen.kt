package com.example.viewmodelmigration.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.example.viewmodelmigration.core.Article
import com.example.viewmodelmigration.core.ArticleListUiState

@Composable
fun ArticleListScreen(
    uiState: ArticleListUiState,
    onArticleClick: (String) -> Unit,
    onDeleteFirstClick: () -> Unit
) {
    Column {
        Button(
            onClick = onDeleteFirstClick,
            modifier = Modifier
                .padding(16.dp)
                .testTag("delete-first-article-button")
        ) {
            Text("Delete first article")
        }

        uiState.articles.forEach { article ->
            ArticleRow(
                article = article,
                onClick = {
                    onArticleClick(article.id)
                }
            )
        }
    }
}

@Composable
private fun ArticleRow(
    article: Article,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .testTag("article-row-${article.id}")
            .clickable(onClick = onClick)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = article.title)
            Text(text = article.body)
        }
    }
}
