package com.imprarce.android.testtaskdelivery

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.imprarce.android.testtaskdelivery.api.Fetch
import com.imprarce.android.testtaskdelivery.model.BannerItem
import com.imprarce.android.testtaskdelivery.model.CategoryItem
import com.imprarce.android.testtaskdelivery.model.MealItem

class MainFragmentViewModel : ViewModel() {
    val mealItemLiveData : LiveData<List<MealItem>>
    val bannerItemLiveData: LiveData<List<BannerItem>>
    val categoryItemLiveData : LiveData<List<CategoryItem>>

    init {
        mealItemLiveData = Fetch().fetchMeal()
        bannerItemLiveData = Fetch().fetchBanner()
        categoryItemLiveData = Fetch().fetchCategory()
    }
}