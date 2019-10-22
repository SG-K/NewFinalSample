package com.m.samplelibb.search.models

import com.squareup.moshi.Json

class MainCategory (@Json(name = "CategoryID")
                    val CategoryID: String? = null,
                    @Json(name = "CategoryName")
                    val CategoryName: String? = "",
                    @Json(name = "Category_Icon")
                    val Category_Icon: String? = "")