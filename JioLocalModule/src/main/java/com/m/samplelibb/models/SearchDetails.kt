package com.m.samplelibb.models

import com.squareup.moshi.Json

data class SearchDetails(
    @field:Json(name = "id")
    val id: Int,
    @field:Json(name = "title")
    val title: String,
    @field:Json(name = "image")
    val image: String,
    @field:Json(name = "address")
    val address: String,
    @field:Json(name = "phone")
    val phone: String,
    @field:Json(name = "handler")
    val handler: String
)