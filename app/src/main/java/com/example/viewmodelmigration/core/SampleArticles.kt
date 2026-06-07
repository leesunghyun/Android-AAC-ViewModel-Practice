package com.example.viewmodelmigration.core

object SampleArticles {
    fun create(): List<Article> {
        return listOf(
            Article(
                id = "1",
                title = "Legacy ViewModel",
                body = "How shared state was handled in the 2017 Android AAC sample."
            ),
            Article(
                id = "2",
                title = "StateFlow Migration",
                body = "How to expose a single state stream for modern Android UIs."
            ),
            Article(
                id = "3",
                title = "Compose UI",
                body = "How to render state consistently with Compose."
            )
        )
    }
}
