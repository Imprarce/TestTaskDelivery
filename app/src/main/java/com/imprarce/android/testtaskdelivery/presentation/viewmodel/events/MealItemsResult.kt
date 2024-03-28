package com.imprarce.android.testtaskdelivery.presentation.viewmodel.events

import androidx.lifecycle.LiveData
import com.imprarce.android.testtaskdelivery.data.model.MealItem

sealed class MealItemsResult {
    data class Success(val items: List<MealItem>) : MealItemsResult()
    data class Error(val message: String) : MealItemsResult()
    object Loading : MealItemsResult()
}
