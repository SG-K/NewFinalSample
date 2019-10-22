package com.m.samplelibb.data

import com.m.samplelibb.models.DashboardModel
import com.m.samplelibb.models.SearchResponse
import com.m.samplelibb.search.models.BusinessDataResponse
import com.m.samplelibb.search.models.MainCategoryResponse
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface APIService {
    @FormUrlEncoded
    @POST("BMV3/GetDashboard")
    fun getSearchResults(@FieldMap params: Map<String, String>): Single<DashboardModel>

    @FormUrlEncoded
    @POST("GetCategories")
    fun getMainCategories(@FieldMap params: Map<String, String>): Single<MainCategoryResponse>

    @FormUrlEncoded
    @POST("BusinessSearch")
    fun getBusinessList(@FieldMap params: Map<String, String>): Single<BusinessDataResponse>

    @FormUrlEncoded
    @POST("BusinessSearch")
    fun getBusinessListNormal(@FieldMap params: Map<String, String>): Call<BusinessDataResponse>
}