package com.imprarce.android.testtaskdelivery.api

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.imprarce.android.testtaskdelivery.api.Response.BannerResponse
import com.imprarce.android.testtaskdelivery.api.Response.CategoryResponse
import com.imprarce.android.testtaskdelivery.api.Response.MealResponse
import com.imprarce.android.testtaskdelivery.api.Response.PhotosForBannerResponse
import com.imprarce.android.testtaskdelivery.model.BannerItem
import com.imprarce.android.testtaskdelivery.model.CategoryItem
import com.imprarce.android.testtaskdelivery.model.MealItem
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

private const val TAG = "ProductFetch"

class Fetch {
    private val productMealApi : ProductApi
    private val productBannerApi : ProductApi
    private val productCategoryApi : ProductApi

    init {
        val retrofitMeal : Retrofit = Retrofit.Builder()
            .baseUrl("https://www.themealdb.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        productMealApi = retrofitMeal.create(ProductApi::class.java)
        productCategoryApi = retrofitMeal.create(ProductApi::class.java)

        val retrofitBanner : Retrofit = Retrofit.Builder()
            .baseUrl("https://api.flickr.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        productBannerApi = retrofitBanner.create(ProductApi::class.java)
    }

    fun fetchMeal(): LiveData<List<MealItem>> {
        val responseLiveData: MutableLiveData<List<MealItem>> = MutableLiveData()
        val flickrRequest : Call<MealResponse> = productMealApi.fetchMeal()

        flickrRequest.enqueue(object : Callback<MealResponse> {
            override fun onFailure(call: Call<MealResponse>, t: Throwable) {
                Log.e(TAG, "Failed to fetch meal", t)
            }

            override fun onResponse(call: Call<MealResponse>, response: Response<MealResponse>) {
                val mealsResponse: MealResponse? = response.body()
                var mealItem : List<MealItem> = mealsResponse?.mealItem ?: mutableListOf()

                mealItem = mealItem.filterNot {
                    it.url.isBlank()
                }

                mealItem.forEach { mealItem ->
                    mealItem.ingredients = mealItem.getAllIngredients()
                }

                responseLiveData.value = mealItem
                Log.d(TAG, "Response received meal")
            }
        })

        return responseLiveData
    }

    fun fetchBanner(): LiveData<List<BannerItem>> {
        val responseLiveData: MutableLiveData<List<BannerItem>> = MutableLiveData()
        val flickrRequest : Call<PhotosForBannerResponse> = productBannerApi.fetchBanner()

        flickrRequest.enqueue(object : Callback<PhotosForBannerResponse> {
            override fun onFailure(call: Call<PhotosForBannerResponse>, t: Throwable) {
                Log.e(TAG, "Failed to fetch banner", t)
            }

            override fun onResponse(call: Call<PhotosForBannerResponse>, response: Response<PhotosForBannerResponse>) {
                val photosForBannerResponse : PhotosForBannerResponse? = response.body()
                val bannerResponse: BannerResponse? = photosForBannerResponse?.photos
                var bannerItem : List<BannerItem> = bannerResponse?.bannerItem ?: mutableListOf()

                bannerItem = bannerItem.filterNot {
                    it.url.isBlank()
                }

                responseLiveData.value = bannerItem
                Log.d(TAG, "Response received banner")
            }
        })

        return responseLiveData
    }

    fun fetchCategory(): LiveData<List<CategoryItem>> {
        val responseLiveData: MutableLiveData<List<CategoryItem>> = MutableLiveData()
        val flickrRequest : Call<CategoryResponse> = productCategoryApi.fetchCategory()

        flickrRequest.enqueue(object : Callback<CategoryResponse> {
            override fun onFailure(call: Call<CategoryResponse>, t: Throwable) {
                Log.e(TAG, "Failed to fetch category", t)
            }

            override fun onResponse(call: Call<CategoryResponse>, response: Response<CategoryResponse>) {
                val mealsResponse: CategoryResponse? = response.body()
                var categoryItem : List<CategoryItem> = mealsResponse?.categoryItem ?: mutableListOf()

                responseLiveData.value = categoryItem
                Log.d(TAG, "Response received category")
            }
        })

        return responseLiveData
    }
}