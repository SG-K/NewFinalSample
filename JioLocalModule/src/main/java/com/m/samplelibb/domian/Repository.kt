package com.m.samplelibb.domian

import com.m.samplelibb.data.APIService
import com.m.samplelibb.models.DashboardModel
import com.m.samplelibb.models.SearchResponse
import com.m.samplelibb.search.models.BusinessDataResponse
import com.m.samplelibb.search.models.MainCategoryResponse
import io.reactivex.Single

interface Repository {

    fun getSerachList(msg : String): String

    fun getDashboard(data : HashMap<String,String>,apiService: APIService): Single<DashboardModel>

    fun getMainCategories(data : HashMap<String,String>,apiService: APIService): Single<MainCategoryResponse>

    fun getBusinessList(data : HashMap<String,String>,apiService: APIService): Single<BusinessDataResponse>

}