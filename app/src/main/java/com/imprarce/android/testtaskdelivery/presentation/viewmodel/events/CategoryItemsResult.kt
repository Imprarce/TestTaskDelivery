package com.imprarce.android.testtaskdelivery.presentation.viewmodel.events

import com.imprarce.android.testtaskdelivery.data.model.CategoryItem

sealed class CategoryItemsResult {
    object Loading : CategoryItemsResult()
    data class Success(val categoryItems: List<CategoryItem>) : CategoryItemsResult()
    data class Error(val errorMessage: String) : CategoryItemsResult()
}