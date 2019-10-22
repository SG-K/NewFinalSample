package com.m.samplelibb.search.models

import com.squareup.moshi.Json

class BusinessData (@Json(name = "MobileNo")
                    val mobileNo: String = "",
                    @Json(name = "Reviews")
                    val reviews: String = "",
                    @Json(name = "BusinessName")
                    val businessName: String = "",
                    @Json(name = "Address")
                    val address: String = "",
                    @Json(name = "PhoneNo")
                    val phoneNo: String = "",
                    @Json(name = "Rating")
                    val rating: String = "",
                    @Json(name = "WebsiteURL")
                    val websiteURL: String = "",
                    @Json(name = "ImageID")
                    val imageID: String? = "",
                    @Json(name = "Distance")
                    val distance: String = "",
                    @Json(name = "BusinessID")
                    val businessID: String = "")