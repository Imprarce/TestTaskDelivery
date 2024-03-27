package com.imprarce.android.testtaskdelivery.api

import retrofit2.Call
import retrofit2.http.GET

interface ProductApi{
    @GET(
        "json/v1/1/search.php?s"
    )
    fun fetchProduct(): Call<MealResponse>
}