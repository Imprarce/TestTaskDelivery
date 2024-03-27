package com.imprarce.android.testtaskdelivery.api.Response

import com.google.gson.annotations.SerializedName
import com.imprarce.android.testtaskdelivery.model.CategoryItem

class CategoryResponse {
    @SerializedName("categories") lateinit var categoryItem: List<CategoryItem>
}