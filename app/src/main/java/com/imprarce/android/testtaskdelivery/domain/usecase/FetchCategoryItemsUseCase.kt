package com.imprarce.android.testtaskdelivery.domain.usecase

import androidx.lifecycle.LiveData
import com.imprarce.android.testtaskdelivery.data.api.Fetch
import com.imprarce.android.testtaskdelivery.data.model.CategoryItem

class FetchCategoryItemsUseCase(private val fetch: Fetch) {
     operator fun invoke(): LiveData<List<CategoryItem>> {
        return fetch.fetchCategory()
    }
}