package com.example.viewmodelmigration

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertDoesNotExist
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.assertIsNotEnabled
import org.junit.Rule
import org.junit.Test

class ArticleUiSmokeTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun editArticle_updatesListImmediatelyAfterSave() {
        composeTestRule.onNodeWithTag("article-row-1").performClick()

        composeTestRule.onNodeWithTag("detail-title-field").performTextClearance()
        composeTestRule.onNodeWithTag("detail-title-field").performTextInput("Legacy ViewModel (Updated)")
        composeTestRule.onNodeWithTag("detail-body-field").performTextClearance()
        composeTestRule.onNodeWithTag("detail-body-field").performTextInput("Updated for smoke test")

        composeTestRule.onNodeWithTag("detail-save-button").performClick()

        composeTestRule.onNodeWithText("Legacy ViewModel (Updated)").assertIsDisplayed()
        composeTestRule.onNodeWithTag("article-row-1").performClick()
        composeTestRule.onNodeWithTag("detail-title-field").assertIsDisplayed()
        composeTestRule.onNodeWithTag("detail-body-field").assertIsDisplayed()
    }

    @Test
    fun deleteFirstArticle_removesFirstRowFromList() {
        composeTestRule.onNodeWithTag("delete-first-article-button").performClick()
        composeTestRule.onNodeWithTag("article-row-1").assertDoesNotExist()
    }

    @Test
    fun backWithoutSave_keepsOriginalArticleInList() {
        composeTestRule.onNodeWithTag("article-row-1").performClick()

        composeTestRule.onNodeWithTag("detail-title-field").performTextClearance()
        composeTestRule.onNodeWithTag("detail-title-field").performTextInput("Legacy ViewModel (Draft)")
        composeTestRule.onNodeWithTag("detail-body-field").performTextClearance()
        composeTestRule.onNodeWithTag("detail-body-field").performTextInput("Draft body")

        composeTestRule.onNodeWithTag("detail-back-button").performClick()

        composeTestRule.onNodeWithText("Legacy ViewModel").assertIsDisplayed()
        composeTestRule.onNodeWithText("Legacy ViewModel (Draft)").assertDoesNotExist()
    }

    @Test
    fun saveButton_isDisabledAndShowsValidation_whenRequiredFieldsAreEmpty() {
        composeTestRule.onNodeWithTag("article-row-1").performClick()

        composeTestRule.onNodeWithTag("detail-title-field").performTextClearance()
        composeTestRule.onNodeWithTag("detail-body-field").performTextClearance()

        composeTestRule.onNodeWithText("Both title and body are required before saving.").assertIsDisplayed()
        composeTestRule.onNodeWithTag("detail-save-button").assertIsNotEnabled()

        composeTestRule.onNodeWithText("Title is required").assertIsDisplayed()
        composeTestRule.onNodeWithText("Body is required").assertIsDisplayed()
    }
}
