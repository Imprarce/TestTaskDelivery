package com.imprarce.android.testtaskdelivery.data.local.db.entities

import com.imprarce.android.testtaskdelivery.data.model.MealItem

data class MealDbEntities (
    val id: Int? = null,
    val title: String,
    val url: String,
    val category: String,
    val ingredients : String
) {
    companion object {
        const val SQL_CREATE_TABLE = "CREATE TABLE IF NOT EXISTS MealDbEntities (id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, url TEXT, category TEXT, ingredients TEXT)"
        const val SQL_DELETE_TABLE = "DROP TABLE IF EXISTS MealDbEntities"
    }
}
