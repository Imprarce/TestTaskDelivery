package com.imprarce.android.testtaskdelivery.data.local.db.entities

import com.imprarce.android.testtaskdelivery.data.model.BannerItem
import com.imprarce.android.testtaskdelivery.data.model.CategoryItem
import com.imprarce.android.testtaskdelivery.data.model.MealItem

    fun BannerItem.toBannerDbEntities(): BannerDbEntities {
        return BannerDbEntities(
            url = this.url
        )
    }

    fun BannerDbEntities.toBannerItem(): BannerItem {
        return BannerItem(
            url = this.url
        )
    }

    fun CategoryDbEntities.toCategoryItem(): CategoryItem {
        return CategoryItem(
            title = this.title
        )
    }

    fun CategoryItem.toCategoryDbEntities(): CategoryDbEntities {
        return CategoryDbEntities(
            title = this.title
        )
    }

    fun MealDbEntities.toMealItem(): MealItem {
        return MealItem(
            title = this.title,
            url = this.url,
            category = this.category,
            ingredients = this.ingredients
        )
    }

    fun MealItem.toMealDbEntities(): MealDbEntities {
        return MealDbEntities(
            title = this.title,
            url = this.url,
            category = this.category,
            ingredients = this.getAllIngredients()
        )
    }