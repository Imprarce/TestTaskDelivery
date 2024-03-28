package com.imprarce.android.testtaskdelivery.data.api.response

import com.google.gson.annotations.SerializedName
import com.imprarce.android.testtaskdelivery.data.model.BannerItem

class BannerResponse {
    @SerializedName("photo") lateinit var bannerItem: List<BannerItem>
}