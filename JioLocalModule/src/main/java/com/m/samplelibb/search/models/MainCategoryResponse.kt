package com.m.samplelibb.search.models

import com.m.samplelibb.models.ListCardsItem
import com.squareup.moshi.Json

class MainCategoryResponse(
    @Json(name = "Error")
    val Error: String? = null,
    @Json(name = "ListCategories")
    val ListCategories: List<MainCategory>?
)