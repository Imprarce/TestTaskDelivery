package com.imprarce.android.testtaskdelivery.data.local.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.imprarce.android.testtaskdelivery.data.local.db.entities.BannerDbEntities
import com.imprarce.android.testtaskdelivery.data.local.db.entities.CategoryDbEntities
import com.imprarce.android.testtaskdelivery.data.local.db.entities.MealDbEntities

class Database(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 2
        private const val DATABASE_NAME = "application.db"
    }

    override fun onCreate(db: SQLiteDatabase) {

        db.execSQL(BannerDbEntities.SQL_CREATE_TABLE)
        db.execSQL(CategoryDbEntities.SQL_CREATE_TABLE)
        db.execSQL(MealDbEntities.SQL_CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

        db.execSQL(BannerDbEntities.SQL_DELETE_TABLE)
        db.execSQL(CategoryDbEntities.SQL_DELETE_TABLE)
        db.execSQL(MealDbEntities.SQL_DELETE_TABLE)
        onCreate(db)
    }


}