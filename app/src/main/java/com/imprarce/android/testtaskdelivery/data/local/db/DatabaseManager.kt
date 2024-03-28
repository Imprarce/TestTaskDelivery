package com.imprarce.android.testtaskdelivery.data.local.db

import android.content.ContentValues
import android.content.Context
import com.imprarce.android.testtaskdelivery.data.local.db.entities.BannerDbEntities
import com.imprarce.android.testtaskdelivery.data.local.db.entities.CategoryDbEntities
import com.imprarce.android.testtaskdelivery.data.local.db.entities.MealDbEntities
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DatabaseManager(context: Context) {

    private val db: Database = Database(context)

    suspend fun insertBannerItem(item: BannerDbEntities) {
        withContext(Dispatchers.IO) {
            val db = db.writableDatabase

            val existingItemCursor = db.query(
                "BannerDbEntities",
                arrayOf("url"),
                "url = ?",
                arrayOf(item.url),
                null,
                null,
                null
            )

            if (existingItemCursor.count > 0) {
                existingItemCursor.close()
                return@withContext
            }

            existingItemCursor.close()

            val values = ContentValues().apply {
                put("id", item.id)
                put("url", item.url)
            }
            db?.insert("BannerDbEntities", null, values)
        }
    }

    suspend fun getBannerItems(): List<BannerDbEntities> {
        return withContext(Dispatchers.IO) {
            val db = db.readableDatabase
            val cursor = db.rawQuery("SELECT * FROM BannerDbEntities", null)
            val items = mutableListOf<BannerDbEntities>()

            with(cursor) {
                while (moveToNext()) {
                    val id = getInt(getColumnIndexOrThrow("id"))
                    val url = getString(getColumnIndexOrThrow("url"))
                    items.add(BannerDbEntities(id, url))
                }
            }

            cursor.close()
            db.close()
            items.toList()
        }
    }

    suspend fun insertCategoryItem(item: CategoryDbEntities) {
        withContext(Dispatchers.IO) {
            val db = db.writableDatabase

            val existingItemCursor = db.query(
                "CategoryDbEntities",
                arrayOf("title"),
                "title = ?",
                arrayOf(item.title),
                null,
                null,
                null
            )

            if (existingItemCursor.count > 0) {
                existingItemCursor.close()
                return@withContext
            }

            existingItemCursor.close()

            val values = ContentValues().apply {
                put("id", item.id)
                put("title", item.title)
            }
            db?.insert("CategoryDbEntities", null, values)
        }
    }


    suspend fun getCategoryItems(): List<CategoryDbEntities> {
        return withContext(Dispatchers.IO) {
            val db = db.readableDatabase
            val cursor = db.rawQuery("SELECT * FROM CategoryDbEntities", null)
            val items = mutableListOf<CategoryDbEntities>()

            with(cursor) {
                while (moveToNext()) {
                    val id = getInt(getColumnIndexOrThrow("id"))
                    val title = getString(getColumnIndexOrThrow("title"))
                    items.add(CategoryDbEntities(id, title))
                }
            }

            cursor.close()
            db.close()
            items.toList()
        }
    }

    suspend fun insertMealItem(item: MealDbEntities) {
        withContext(Dispatchers.IO) {
            val db = db.writableDatabase

            val existingItemCursor = db.query(
                "MealDbEntities",
                arrayOf("title"),
                "title = ?",
                arrayOf(item.title),
                null,
                null,
                null
            )

            if (existingItemCursor.count > 0) {
                existingItemCursor.close()
                return@withContext
            }

            existingItemCursor.close()

            val values = ContentValues().apply {
                put("title", item.title)
                put("url", item.url)
                put("category", item.category)
                put("ingredients", item.ingredients)
            }
            db?.insert("MealDbEntities", null, values)
        }
    }

    suspend fun getMealItems(): List<MealDbEntities> {
        return withContext(Dispatchers.IO) {
            val db = db.readableDatabase
            val cursor = db.rawQuery("SELECT * FROM MealDbEntities", null)
            val items = mutableListOf<MealDbEntities>()

            with(cursor) {
                while (moveToNext()) {
                    val id = getInt(getColumnIndexOrThrow("id"))
                    val title = getString(getColumnIndexOrThrow("title"))
                    val url = getString(getColumnIndexOrThrow("url"))
                    val category = getString(getColumnIndexOrThrow("category"))
                    val ingredients = getString(getColumnIndexOrThrow("ingredients"))
                    items.add(MealDbEntities(id, title, url, category, ingredients))
                }
            }

            cursor.close()
            db.close()
            items.toList()
        }
    }

    fun hasMealItems(): Boolean {
        val db = db.readableDatabase
        val cursor = db.rawQuery("SELECT COUNT(*) FROM MealDbEntities", null)
        cursor.moveToFirst()
        val count = cursor.getInt(0)
        cursor.close()
        return count > 0
    }

    suspend fun hasBannerItems(): Boolean {
        return withContext(Dispatchers.IO) {
            val db = db.readableDatabase
            val cursor = db.rawQuery("SELECT COUNT(*) FROM BannerDbEntities", null)
            cursor.moveToFirst()
            val count = cursor.getInt(0)
            cursor.close()
            count > 0
        }
    }

    suspend fun hasCategoryItems(): Boolean {
        return withContext(Dispatchers.IO) {
            val db = db.readableDatabase
            val cursor = db.rawQuery("SELECT COUNT(*) FROM CategoryDbEntities", null)
            cursor.moveToFirst()
            val count = cursor.getInt(0)
            cursor.close()
            count > 0
        }
    }

}
