package com.imprarce.android.testtaskdelivery.data.api.response

import com.google.gson.annotations.SerializedName
import com.imprarce.android.testtaskdelivery.data.model.CategoryItem

class CategoryResponse {
    @SerializedName("categories") lateinit var categoryItem: List<CategoryItem>
}