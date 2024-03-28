package com.imprarce.android.testtaskdelivery.dependency

import android.content.Context
import com.imprarce.android.testtaskdelivery.data.api.Fetch
import com.imprarce.android.testtaskdelivery.data.local.db.DatabaseManager
import com.imprarce.android.testtaskdelivery.data.repository.MainRepositoryImpl
import com.imprarce.android.testtaskdelivery.domain.usecase.FetchBannerItemsUseCase
import com.imprarce.android.testtaskdelivery.domain.usecase.FetchCategoryItemsUseCase
import com.imprarce.android.testtaskdelivery.domain.usecase.FetchMealItemsUseCase

class DependencyProviderImpl(private val context: Context) : DependencyProvider {
    override val mainRepository: MainRepositoryImpl by lazy {
        val fetch = Fetch()
        val fetchBannerItemsUseCase = FetchBannerItemsUseCase(fetch)
        val fetchMealItemsUseCase = FetchMealItemsUseCase(fetch)
        val fetchCategoryItemsUseCase = FetchCategoryItemsUseCase(fetch)
        MainRepositoryImpl(fetchBannerItemsUseCase, fetchMealItemsUseCase, fetchCategoryItemsUseCase, context)
    }

    override val databaseManager: DatabaseManager by lazy {
        DatabaseManager(context)
    }
}