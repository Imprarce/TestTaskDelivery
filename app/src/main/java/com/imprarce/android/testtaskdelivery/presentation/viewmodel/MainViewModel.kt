package com.imprarce.android.testtaskdelivery.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imprarce.android.testtaskdelivery.data.local.db.DatabaseManager
import com.imprarce.android.testtaskdelivery.data.local.db.entities.toMealItem
import com.imprarce.android.testtaskdelivery.data.model.BannerItem
import com.imprarce.android.testtaskdelivery.data.model.CategoryItem
import com.imprarce.android.testtaskdelivery.data.model.MealItem
import com.imprarce.android.testtaskdelivery.data.repository.MainRepositoryImpl
import com.imprarce.android.testtaskdelivery.presentation.viewmodel.events.BannerItemsResult
import com.imprarce.android.testtaskdelivery.presentation.viewmodel.events.CategoryItemsResult
import com.imprarce.android.testtaskdelivery.presentation.viewmodel.events.FilterEvent
import com.imprarce.android.testtaskdelivery.presentation.viewmodel.events.MealItemsResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val TAG = "MainViewModel"

class MainViewModel(
    private val repository: MainRepositoryImpl?,
    private val dbManager: DatabaseManager?
) : ViewModel() {

    var mealItemsLiveData : LiveData<List<MealItem>> = MutableLiveData()
    var bannerItemsLiveData: LiveData<List<BannerItem>> = MutableLiveData()
    var categoryItemsLiveData : LiveData<List<CategoryItem>> = MutableLiveData()
    var selectedCategoryLiveData = MutableLiveData<String>()
    private val _filterEvent = MutableLiveData<FilterEvent>()

    val filterEvent: LiveData<FilterEvent>
        get() = _filterEvent

    init {
        viewModelScope.launch {
            mealItemsLiveData = fetchMealItems()
            bannerItemsLiveData = fetchBannerItems()
            categoryItemsLiveData = fetchCategoryItems()

            Log.d(TAG, "${mealItemsLiveData.value} --- ${bannerItemsLiveData.value} --- ${categoryItemsLiveData.value}")
        }
    }

    private fun fetchMealItems(): LiveData<List<MealItem>> {
        val liveData = MutableLiveData<List<MealItem>>()
        viewModelScope.launch {
            repository?.fetchMealItems()?.observeForever { result ->
                when (result) {
                    is MealItemsResult.Success -> {
                        liveData.value = result.items
                        Log.d(TAG, "Meal items updated: ${result.items}")
                    }
                    is MealItemsResult.Error -> {
                        Log.e(TAG, "Error fetching meal items: ${result.message}")
                    }
                    MealItemsResult.Loading -> {
                        Log.d(TAG, "Loading meal items...")
                    }
                }
            }
        }
        return liveData
    }

    private fun fetchBannerItems(): LiveData<List<BannerItem>> {
        val liveData = MutableLiveData<List<BannerItem>>()
        viewModelScope.launch {
            repository?.fetchBannerItems()?.observeForever { result ->
                when (result) {
                    is BannerItemsResult.Success -> {
                        liveData.value = result.bannerItems
                        Log.d(TAG, "Banner items updated: ${result.bannerItems}")
                    }
                    is BannerItemsResult.Error -> {
                        Log.e(TAG, "Error fetching banner items: ${result.errorMessage}")
                    }
                    BannerItemsResult.Loading -> {
                        Log.d(TAG, "Loading banner items...")
                    }
                }
            }
        }
        return liveData
    }

    private fun fetchCategoryItems(): LiveData<List<CategoryItem>> {
        val liveData = MutableLiveData<List<CategoryItem>>()
        viewModelScope.launch {
            repository?.fetchCategoryItems()?.observeForever { result ->
                when (result) {
                    is CategoryItemsResult.Success -> {
                        liveData.value = result.categoryItems
                        Log.d(TAG, "Category items updated: ${result.categoryItems}")
                    }
                    is CategoryItemsResult.Error -> {
                        Log.e(TAG, "Error fetching category items: ${result.errorMessage}")
                    }
                    CategoryItemsResult.Loading -> {
                        Log.d(TAG, "Loading category items...")
                    }
                }
            }
        }
        return liveData
    }

    fun setSelectedCategory(filterEvent: FilterEvent) {
        when (filterEvent) {
            is FilterEvent.Cleared -> clearFilter()
            is FilterEvent.Selected -> filterItemsByCategory(filterEvent.categoryName)
            else -> {}
        }
    }

    fun filterItemsByCategory(categoryName: String) {
        val filteredList = if (categoryName.isNotEmpty()) {
            mealItemsLiveData.value?.filter { it.category == categoryName } ?: emptyList()
        } else {
            mealItemsLiveData.value ?: emptyList()
        }
        (mealItemsLiveData as MutableLiveData).value = filteredList
        Log.d(TAG, "Filtered")
    }

    private fun clearFilter()  {
        viewModelScope.launch {
            val mealItemsFromDb = withContext(Dispatchers.Default) {
                if (dbManager?.hasMealItems() == true) {
                    dbManager.getMealItems().map { it.toMealItem() }
                } else {
                    emptyList()
                }
            }
            (mealItemsLiveData as MutableLiveData).value = mealItemsFromDb
            Log.d(TAG, "Cleared filter. Updated meal items from local database.")
            _filterEvent.value = FilterEvent.Cleared
        }
    }

}


