package com.imprarce.android.testtaskdelivery.data.model

import com.google.gson.annotations.SerializedName

data class MealItem(
    @SerializedName("strMeal") var title: String = "",
    @SerializedName("idMeal") var id: String = "",
    @SerializedName("strMealThumb") var url : String = "",
    @SerializedName("strCategory") var category : String = "",
    @SerializedName("strIngredient1") var ingredient1: String = "",
    @SerializedName("strIngredient2") var ingredient2: String = "",
    @SerializedName("strIngredient3") var ingredient3: String = "",
    @SerializedName("strIngredient4") var ingredient4: String = "",
    @SerializedName("strIngredient5") var ingredient5: String = "",
    @SerializedName("strIngredient6") var ingredient6: String = "",
    @SerializedName("strIngredient7") var ingredient7: String = "",
    @SerializedName("strIngredient8") var ingredient8: String = "",
    @SerializedName("strIngredient9") var ingredient9: String = "",
    @SerializedName("strIngredient10") var ingredient10: String = "",
    @SerializedName("strIngredient11") var ingredient11: String = "",
    @SerializedName("strIngredient12") var ingredient12: String = "",
    @SerializedName("strIngredient13") var ingredient13: String = "",
    @SerializedName("strIngredient14") var ingredient14: String = "",
    @SerializedName("strIngredient15") var ingredient15: String = "",
    @SerializedName("strIngredient16") var ingredient16: String = "",
    @SerializedName("strIngredient17") var ingredient17: String = "",
    @SerializedName("strIngredient18") var ingredient18: String = "",
    @SerializedName("strIngredient19") var ingredient19: String = "",
    @SerializedName("strIngredient20") var ingredient20: String = "",
    var ingredients: String = ""
) {

    fun getAllIngredients(): String {
        val ingredientsList = mutableListOf<String>()
        if (!ingredient1.isNullOrBlank()) ingredientsList.add(ingredient1)
        if (!ingredient2.isNullOrBlank()) ingredientsList.add(ingredient2)
        if (!ingredient3.isNullOrBlank()) ingredientsList.add(ingredient3)
        if (!ingredient4.isNullOrBlank()) ingredientsList.add(ingredient4)
        if (!ingredient5.isNullOrBlank()) ingredientsList.add(ingredient5)
        if (!ingredient6.isNullOrBlank()) ingredientsList.add(ingredient6)
        if (!ingredient7.isNullOrBlank()) ingredientsList.add(ingredient7)
        if (!ingredient8.isNullOrBlank()) ingredientsList.add(ingredient8)
        if (!ingredient9.isNullOrBlank()) ingredientsList.add(ingredient9)
        if (!ingredient10.isNullOrBlank()) ingredientsList.add(ingredient10)
        if (!ingredient11.isNullOrBlank()) ingredientsList.add(ingredient11)
        if (!ingredient12.isNullOrBlank()) ingredientsList.add(ingredient12)
        if (!ingredient13.isNullOrBlank()) ingredientsList.add(ingredient13)
        if (!ingredient14.isNullOrBlank()) ingredientsList.add(ingredient14)
        if (!ingredient15.isNullOrBlank()) ingredientsList.add(ingredient15)
        if (!ingredient16.isNullOrBlank()) ingredientsList.add(ingredient16)
        if (!ingredient17.isNullOrBlank()) ingredientsList.add(ingredient17)
        if (!ingredient18.isNullOrBlank()) ingredientsList.add(ingredient18)
        if (!ingredient19.isNullOrBlank()) ingredientsList.add(ingredient19)
        if (!ingredient20.isNullOrBlank()) ingredientsList.add(ingredient20)
        return ingredientsList.joinToString(", ")
    }
}
