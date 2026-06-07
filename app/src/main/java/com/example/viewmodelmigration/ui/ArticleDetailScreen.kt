package com.example.viewmodelmigration.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.viewmodelmigration.core.Article

@Composable
fun ArticleDetailScreen(
    article: Article,
    onSaveClick: (Article) -> Unit,
    onBackClick: () -> Unit
) {
    var titleState by remember(article.id) {
        mutableStateOf(article.title)
    }

    var bodyState by remember(article.id) {
        mutableStateOf(article.body)
    }

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text("Edit Article")

        OutlinedTextField(
            value = titleState,
            onValueChange = {
                titleState = it
            },
            label = {
                Text("Title")
            },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = bodyState,
            onValueChange = {
                bodyState = it
            },
            label = {
                Text("Body")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                onSaveClick(
                    article.copy(
                        title = titleState,
                        body = bodyState
                    )
                )
            }
        ) {
            Text("Save")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = onBackClick
        ) {
            Text("Back")
        }
    }
}
