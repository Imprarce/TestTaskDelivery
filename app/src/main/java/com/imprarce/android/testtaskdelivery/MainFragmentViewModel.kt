package com.imprarce.android.testtaskdelivery

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.imprarce.android.testtaskdelivery.model.MealItem

class MainFragmentViewModel : ViewModel() {
    val mealItemLiveData : LiveData<List<MealItem>>

    init {
        mealItemLiveData = ProductFetch().fetchProduct()
    }
}