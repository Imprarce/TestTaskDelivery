package com.imprarce.android.testtaskdelivery.data.repository

import androidx.lifecycle.LiveData
import com.imprarce.android.testtaskdelivery.data.model.BannerItem
import com.imprarce.android.testtaskdelivery.data.model.CategoryItem
import com.imprarce.android.testtaskdelivery.data.model.MealItem
import com.imprarce.android.testtaskdelivery.presentation.viewmodel.events.BannerItemsResult
import com.imprarce.android.testtaskdelivery.presentation.viewmodel.events.CategoryItemsResult
import com.imprarce.android.testtaskdelivery.presentation.viewmodel.events.MealItemsResult

interface MainRepository {
    suspend fun fetchBannerItems(): LiveData<BannerItemsResult>
    suspend fun fetchMealItems(): LiveData<MealItemsResult>
    suspend fun fetchCategoryItems(): LiveData<CategoryItemsResult>
}