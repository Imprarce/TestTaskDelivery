package com.imprarce.android.testtaskdelivery.data.api.response

import com.google.gson.annotations.SerializedName
import com.imprarce.android.testtaskdelivery.data.model.MealItem

class MealResponse {
    @SerializedName("meals") lateinit var mealItem: List<MealItem>
}