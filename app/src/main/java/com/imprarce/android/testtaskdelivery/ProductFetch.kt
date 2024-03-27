package com.imprarce.android.testtaskdelivery

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.imprarce.android.testtaskdelivery.api.MealResponse
import com.imprarce.android.testtaskdelivery.api.ProductApi
import com.imprarce.android.testtaskdelivery.model.MealItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val TAG = "ProductFetch"

class ProductFetch {
    private val productApi : ProductApi

    init {
        val retrofit : Retrofit = Retrofit.Builder()
            .baseUrl("https://www.themealdb.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        productApi = retrofit.create(ProductApi::class.java)
    }

    fun fetchProduct(): LiveData<List<MealItem>> {
        val responseLiveData: MutableLiveData<List<MealItem>> = MutableLiveData()
        val flickrRequest : Call<MealResponse> = productApi.fetchProduct()

        flickrRequest.enqueue(object : Callback<MealResponse> {
            override fun onFailure(call: Call<MealResponse>, t: Throwable) {
                Log.e(TAG, "Failed to fetch product", t)
            }

            override fun onResponse(call: Call<MealResponse>, response: Response<MealResponse>) {
                val mealsResponse: MealResponse? = response.body()
                var mealItem : List<MealItem> = mealsResponse?.mealItem ?: mutableListOf()
                mealItem = mealItem.filterNot {
                    it.url.isBlank()
                }
                responseLiveData.value = mealItem
                Log.d(TAG, "Response received")
            }
        })

        return responseLiveData
    }
}