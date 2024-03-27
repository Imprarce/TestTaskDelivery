package com.imprarce.android.testtaskdelivery.api

import com.google.gson.annotations.SerializedName
import com.imprarce.android.testtaskdelivery.model.MealItem

class MealResponse {
    @SerializedName("meals") lateinit var mealItem: List<MealItem>
}