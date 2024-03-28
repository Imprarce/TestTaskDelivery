package com.imprarce.android.testtaskdelivery.data.local.db.entities

import com.imprarce.android.testtaskdelivery.data.model.BannerItem

data class BannerDbEntities (
    val id: Int? = null,
    val url: String
) {
    companion object {
        const val SQL_CREATE_TABLE = "CREATE TABLE IF NOT EXISTS BannerDbEntities (id INTEGER PRIMARY KEY AUTOINCREMENT, url TEXT)"
        const val SQL_DELETE_TABLE = "DROP TABLE IF EXISTS BannerDbEntities"
    }
}
