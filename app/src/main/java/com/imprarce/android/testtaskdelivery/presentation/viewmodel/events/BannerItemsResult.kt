package com.imprarce.android.testtaskdelivery.presentation.viewmodel.events

import com.imprarce.android.testtaskdelivery.data.model.BannerItem

sealed class BannerItemsResult {
    object Loading : BannerItemsResult()
    data class Success(val bannerItems: List<BannerItem>) : BannerItemsResult()
    data class Error(val errorMessage: String) : BannerItemsResult()
}