package com.example.viewmodelmigration.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
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

    val isTitleInvalid = titleState.isBlank()
    val isBodyInvalid = bodyState.isBlank()
    val canSave = titleState.isNotBlank() && bodyState.isNotBlank()

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text("Edit Article")

        OutlinedTextField(
            value = titleState,
            modifier = Modifier
                .fillMaxWidth()
                .testTag("detail-title-field"),
            onValueChange = {
                titleState = it
            },
            isError = isTitleInvalid,
            label = {
                Text("Title")
            },
            supportingText = if (isTitleInvalid) {
                {
                    Text("Title is required")
                }
            } else {
                null
            }
        )

        OutlinedTextField(
            value = bodyState,
            modifier = Modifier
                .fillMaxWidth()
                .testTag("detail-body-field"),
            onValueChange = {
                bodyState = it
            },
            isError = isBodyInvalid,
            label = {
                Text("Body")
            },
            supportingText = if (isBodyInvalid) {
                {
                    Text("Body is required")
                }
            } else {
                null
            }
        )

        if (!canSave) {
            Text(
                text = "Both title and body are required before saving.",
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        Button(
            modifier = Modifier.testTag("detail-save-button"),
            enabled = canSave,
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
            modifier = Modifier.testTag("detail-back-button"),
            onClick = onBackClick
        ) {
            Text("Back")
        }
    }
}
