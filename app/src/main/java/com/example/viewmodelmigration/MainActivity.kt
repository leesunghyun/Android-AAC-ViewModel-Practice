package com.example.viewmodelmigration

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.viewmodelmigration.ui.ArticleDetailScreen
import com.example.viewmodelmigration.ui.ArticleListScreen
import com.example.viewmodelmigration.viewmodel.ArticleListViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                AppRoot()
            }
        }
    }
}

@Composable
private fun AppRoot(
    viewModel: ArticleListViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val selectedArticle = uiState.articles.firstOrNull {
        it.id == uiState.selectedArticleId
    }

    if (selectedArticle == null) {
        ArticleListScreen(
            uiState = uiState,
            onArticleClick = { articleId ->
                viewModel.selectArticle(articleId)
            },
            onDeleteFirstClick = {
                val firstArticle = uiState.articles.firstOrNull()
                if (firstArticle != null) {
                    viewModel.deleteArticle(firstArticle.id)
                }
            }
        )
    } else {
        ArticleDetailScreen(
            article = selectedArticle,
            onSaveClick = { updatedArticle ->
                viewModel.updateArticle(updatedArticle)
                viewModel.clearSelection()
            },
            onBackClick = {
                viewModel.clearSelection()
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun AppRootPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Preview")
        Button(
            onClick = {}
        ) {
            Text("List")
        }
    }
}
