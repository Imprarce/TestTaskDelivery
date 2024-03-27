package com.imprarce.android.testtaskdelivery.model

import com.google.gson.annotations.SerializedName

data class MealItem(
    @SerializedName("strMeal") var title: String = "",
    @SerializedName("idMeal") var id: String = "",
    @SerializedName("strMealThumb") var url : String = ""
)
