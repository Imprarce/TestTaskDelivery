package com.imprarce.android.testtaskdelivery.data.local.db.entities

import com.imprarce.android.testtaskdelivery.data.model.CategoryItem

data class CategoryDbEntities (
    val id: Int? = null,
    val title: String
) {
    companion object {
        const val SQL_CREATE_TABLE = "CREATE TABLE IF NOT EXISTS CategoryDbEntities (id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT)"
        const val SQL_DELETE_TABLE = "DROP TABLE IF EXISTS CategoryDbEntities"
    }
}