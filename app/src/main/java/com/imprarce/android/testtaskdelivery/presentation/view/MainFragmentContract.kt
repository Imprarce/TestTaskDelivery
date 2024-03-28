package com.imprarce.android.testtaskdelivery.presentation.view

import com.imprarce.android.testtaskdelivery.data.model.BannerItem
import com.imprarce.android.testtaskdelivery.data.model.CategoryItem
import com.imprarce.android.testtaskdelivery.data.model.MealItem

interface MainFragmentContract {
    fun showBannerItems(bannerItems: List<BannerItem>)
    fun showMealItems(mealItems: List<MealItem>)
    fun showCategoryChips(categories: List<CategoryItem>)
}