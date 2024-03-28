package com.imprarce.android.testtaskdelivery.data.api

import com.imprarce.android.testtaskdelivery.data.api.response.CategoryResponse
import com.imprarce.android.testtaskdelivery.data.api.response.MealResponse
import com.imprarce.android.testtaskdelivery.data.api.response.PhotosForBannerResponse
import retrofit2.Call
import retrofit2.http.GET

interface ProductApi{
    @GET(
        "json/v1/1/search.php?s"
    )
    fun fetchMeal(): Call<MealResponse>

    @GET(
        "json/v1/1/categories.php"
    )
    fun fetchCategory(): Call<CategoryResponse>

    @GET(
        "services/rest/?method=flickr.interestingness.getList" +
                "&api_key=092a9bfaf50a5c140b61de4f773fa015" +
                "&format=json" +
                "&nojsoncallback=1" +
                "&extras=url_s"
    )
    fun fetchBanner(): Call<PhotosForBannerResponse>
}