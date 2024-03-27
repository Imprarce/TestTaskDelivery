package com.imprarce.android.testtaskdelivery.model

import com.google.gson.annotations.SerializedName

data class CategoryItem(
    @SerializedName("idCategory") var id : String = "",
    @SerializedName("strCategory") var title : String = ""
)
