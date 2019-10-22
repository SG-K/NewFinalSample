package com.m.samplelibb.search.models

import com.squareup.moshi.Json

class BusinessDataResponse(
    @Json(name = "Error")
    val Error: String? = null,
    @Json(name = "ListBusinesses")
    val ListBusinesses: List<BusinessData>?
)