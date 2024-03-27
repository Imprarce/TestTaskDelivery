package com.imprarce.android.testtaskdelivery.api.Response

import com.google.gson.annotations.SerializedName
import com.imprarce.android.testtaskdelivery.model.BannerItem

class BannerResponse {
    @SerializedName("photo") lateinit var bannerItem: List<BannerItem>
}