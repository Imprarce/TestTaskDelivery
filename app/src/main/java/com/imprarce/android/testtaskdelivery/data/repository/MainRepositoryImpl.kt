package com.imprarce.android.testtaskdelivery.data.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.imprarce.android.testtaskdelivery.data.local.db.DatabaseManager
import com.imprarce.android.testtaskdelivery.data.local.db.entities.*
import com.imprarce.android.testtaskdelivery.domain.usecase.FetchBannerItemsUseCase
import com.imprarce.android.testtaskdelivery.domain.usecase.FetchCategoryItemsUseCase
import com.imprarce.android.testtaskdelivery.domain.usecase.FetchMealItemsUseCase
import com.imprarce.android.testtaskdelivery.presentation.viewmodel.events.BannerItemsResult
import com.imprarce.android.testtaskdelivery.presentation.viewmodel.events.CategoryItemsResult
import com.imprarce.android.testtaskdelivery.presentation.viewmodel.events.MealItemsResult
import com.imprarce.android.testtaskdelivery.utils.NetworkUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

private const val TAG = "MainRepositoryImpl"

class MainRepositoryImpl(
    private val fetchBannerItemsUseCase: FetchBannerItemsUseCase,
    private val fetchMealItemsUseCase: FetchMealItemsUseCase,
    private val fetchCategoryItemsUseCase: FetchCategoryItemsUseCase,
    private val context: Context
) : MainRepository {
    private val networkUtils: NetworkUtils = NetworkUtils
    private val databaseManager: DatabaseManager = DatabaseManager(context)


    override suspend fun fetchBannerItems(): LiveData<BannerItemsResult> {
        return if (networkUtils.isConnected(context)) {
            try {
                val bannerItems = fetchBannerItemsUseCase()

                val timeoutMillis = 1500L

                withContext(Dispatchers.IO) {
                    delay(timeoutMillis)
                }

                if (bannerItems != null) {
                    bannerItems.value?.forEach { bannerItem ->
                        val bannerDbEntity = bannerItem.toBannerDbEntities()
                        databaseManager.insertBannerItem(bannerDbEntity)
                    }
                    MutableLiveData<BannerItemsResult>().apply {
                        value = BannerItemsResult.Success(bannerItems.value ?: emptyList())
                        Log.d(TAG, "$value ---- ${fetchBannerItemsUseCase.invoke().value}")
                    }
                } else {
                    // Тайм-аут истек
                    MutableLiveData<BannerItemsResult>().apply {
                        value = BannerItemsResult.Error("Timeout while fetching banner items")
                    }
                }
            } catch (e: Exception) {
                MutableLiveData<BannerItemsResult>().apply {
                    value = BannerItemsResult.Error(e.message ?: "Unknown error")
                }
            }
        } else {
            if(!databaseManager.hasBannerItems()) MutableLiveData<BannerItemsResult>()
            val bannerDbEntities = databaseManager.getBannerItems()
            if (bannerDbEntities.isNotEmpty()) {
                MutableLiveData<BannerItemsResult>().apply {
                    value = BannerItemsResult.Success(bannerDbEntities.map { it.toBannerItem() })
                }
            } else {
                MutableLiveData<BannerItemsResult>()
            }
        }
    }

    override suspend fun fetchMealItems(): LiveData<MealItemsResult> {
        return if (networkUtils.isConnected(context)) {
            try {
                val mealItems = fetchMealItemsUseCase()

                val timeoutMillis = 1500L

                withContext(Dispatchers.IO) {
                    delay(timeoutMillis)
                }

                mealItems.value?.forEach { mealItem ->
                    val mealDbEntity = mealItem.toMealDbEntities()
                    databaseManager.insertMealItem(mealDbEntity)
                }
                MutableLiveData<MealItemsResult>().apply {
                    value = MealItemsResult.Success(mealItems.value ?: emptyList())
                }
            } catch (e: Exception) {
                MutableLiveData<MealItemsResult>().apply {
                    value = MealItemsResult.Error(e.message ?: "Unknown error")
                }
            }
        } else {
            if(!databaseManager.hasMealItems()) MutableLiveData<MealItemsResult>()
            val mealDbEntities = databaseManager.getMealItems()
            if (mealDbEntities.isNotEmpty()) {
                MutableLiveData<MealItemsResult>().apply {
                    value = MealItemsResult.Success(mealDbEntities.map { it.toMealItem() })
                }
            } else {
                MutableLiveData<MealItemsResult>()
            }
        }
    }


    override suspend fun fetchCategoryItems(): LiveData<CategoryItemsResult> {
        return if (networkUtils.isConnected(context)) {
            try {
                val categoryItems = fetchCategoryItemsUseCase()

                val timeoutMillis = 1500L

                withContext(Dispatchers.IO) {
                    delay(timeoutMillis)
                }

                categoryItems.value?.forEach { categoryItem ->
                    val categoryDbEntity = categoryItem.toCategoryDbEntities()
                    databaseManager.insertCategoryItem(categoryDbEntity)
                }
                MutableLiveData<CategoryItemsResult>().apply {
                    value = CategoryItemsResult.Success(categoryItems.value ?: emptyList())
                }
            } catch (e: Exception) {
                MutableLiveData<CategoryItemsResult>().apply {
                    value = CategoryItemsResult.Error(e.message ?: "Unknown error")
                }
            }
        } else {
            if(!databaseManager.hasCategoryItems()) MutableLiveData<CategoryItemsResult>()
            val categoryDbEntities = databaseManager.getCategoryItems()
            if (categoryDbEntities.isNotEmpty()) {
                MutableLiveData<CategoryItemsResult>().apply {
                    value = CategoryItemsResult.Success(categoryDbEntities.map { it.toCategoryItem() })
                }
            } else {
                MutableLiveData<CategoryItemsResult>()
            }
        }
    }
}
