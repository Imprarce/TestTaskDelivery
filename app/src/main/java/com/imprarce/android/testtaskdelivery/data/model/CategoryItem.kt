package com.imprarce.android.testtaskdelivery.data.model

import com.google.gson.annotations.SerializedName

data class CategoryItem(
    @SerializedName("idCategory") var id : String = "",
    @SerializedName("strCategory") var title : String = ""
)
