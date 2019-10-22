package com.m.samplelibb.data

import android.util.Log
import com.m.samplelibb.domian.Repository
import com.m.samplelibb.models.DashboardModel
import com.m.samplelibb.models.SearchResponse
import com.m.samplelibb.search.models.BusinessDataResponse
import com.m.samplelibb.search.models.MainCategoryResponse
import io.reactivex.Single
//import timber.log.Timber

class RepositoryImpl : Repository {
    override fun getBusinessList(data: HashMap<String, String>, apiService: APIService): Single<BusinessDataResponse> {
        return apiService.getBusinessList(data)
    }


    override fun getMainCategories(
        data: HashMap<String, String>,
        apiService: APIService
    ): Single<MainCategoryResponse> {
        return apiService.getMainCategories(data)
    }


    override fun getDashboard(data: HashMap<String, String>,apiService: APIService): Single<DashboardModel> {
        return apiService.getSearchResults(data)
    }

    override fun getSerachList(msg: String): String {
        Log.v("jhdvbhjsdbvhjbvjhsd","got_inot_repo 1")
//        Timber.v("jhdvbhjsdbvhjbvjhsd got_inot_repo %s",msg)
        return msg
    }
}