package com.m.samplelibb.search.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.m.samplelibb.TextUtils
import com.m.samplelibb.base.BaseViewModel
import com.m.samplelibb.search.models.BusinessData
import com.m.samplelibb.search.models.BusinessDataResponse
import com.m.samplelibb.search.models.MainCategory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber


class CategorySearchViewModel : BaseViewModel() {


    var arrayCategoriesLiveData = MutableLiveData<ArrayList<MainCategory>>()
    var arrayCategories: ArrayList<MainCategory> = ArrayList()



    var errorLiveData = MutableLiveData<String>()

    var arrayBusinessLiveData = MutableLiveData<ArrayList<BusinessData>>()
    var arrayBusiness: ArrayList<BusinessData> = ArrayList()

    var hasMoreItems: Boolean = true
    var isLoading: Boolean = true
    var page: Int = 1
    var catgoryID: String = ""

    fun getResults(context: Context) {
        TextUtils.printTimberLog("got into service")
        val hashMap_daata: HashMap<String, String> = HashMap()
        hashMap_daata["MobileNo"] = "9885084010"
        hashMap_daata["Latitude"] = "149871843469177"
        hashMap_daata["Longitude"] = "181758753851533"
        TextUtils.printTimberLog("got into service 1")

        try {
            val view_moshi = getMoshi()

            TextUtils.printTimberLog("got into service 2")

            val predds = providePreferenceHelper(context)

            TextUtils.printTimberLog("got into service 3")

            val okhhbbjs = provideOkHttpClient(
                predds, view_moshi
            )

            TextUtils.printTimberLog("got into service 4")

            val retrojfknfv = provideRetrofit(okhhbbjs, view_moshi)

            TextUtils.printTimberLog("got into service 5")

            val apijkdvnkj = provideApiService(retrojfknfv)

            TextUtils.printTimberLog("got into service 6")

            TextUtils.printTimberLog("got into service 7")
            getRepo()?.getMainCategories(hashMap_daata, apijkdvnkj)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe({
                    //                    Timber.v("request sucess")
                    TextUtils.printTimberLog("got into service sucess")
                    it?.let { categriesModel ->
                        if (categriesModel.Error == null) {
                            TextUtils.printTimberLog("request sucess views ${categriesModel.ListCategories?.size}")
                            TextUtils.printTimberLog("request sucess total $categriesModel")
                            categriesModel.ListCategories?.let {
                                arrayCategories = it as ArrayList
                                arrayCategoriesLiveData.postValue(categriesModel.ListCategories as ArrayList)
                            }

                        } else {
                            TextUtils.printTimberLog("request fail  ${categriesModel.Error}")
                        }
                    }
                }, {
                    TextUtils.printTimberLog("got into non-server fail  ${it.message}")
                }).let {
                    it?.let {
                        getCompositeDisposable().add(it)
                    }
                }
        } catch (e: Exception) {
            e.printStackTrace()
            TextUtils.printTimberLog("got into exception  ${e.message}")
        }


    }

    fun getBusinessList(context: Context) {
        isLoading = true
        TextUtils.printTimberLog("got into service")
        val hashMap_daata: HashMap<String, String> = HashMap()
        hashMap_daata["CategoryID"] = catgoryID
        hashMap_daata["AccountID"] = "149871843469177"
        hashMap_daata["Keyword"] = ""
        hashMap_daata["CityID"] = ""
        hashMap_daata["Latitude"] = ""
        hashMap_daata["Longitude"] = ""
        hashMap_daata["PageNo"] = page.toString()
        TextUtils.printTimberLog("got into service 1")

        try {
            getRepo()?.getBusinessList(hashMap_daata, apiService!!)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe({
                    //                    Timber.v("request sucess")
                    TextUtils.printTimberLog("got into service sucess")
                    it?.let { categriesModel ->
                        if (categriesModel.Error == null) {
                            TextUtils.printTimberLog("request sucess views ${categriesModel.ListBusinesses?.size}")
                            TextUtils.printTimberLog("request sucess total $categriesModel")

                            categriesModel.ListBusinesses?.forEach {
                                Timber.v("Business_nammmee %s", it.businessName)
                            }

                            categriesModel.ListBusinesses?.let {
                                hasMoreItems = it.isNotEmpty()
                                if (page == 1) {
                                    arrayBusiness = it as ArrayList
                                } else {
                                    arrayBusiness.addAll(it as ArrayList)
                                }
                                arrayBusinessLiveData.postValue(categriesModel.ListBusinesses as ArrayList)
                            }

                        } else {
                            TextUtils.printTimberLog("request fail  ${categriesModel.Error}")
                        }
                    }
                    isLoading = false
                }, {
                    isLoading = false
                    TextUtils.printTimberLog("got into non-server fail  ${it.message}")
                }).let {
                    it?.let {

                        getCompositeDisposable().add(it)
                    }
                }
        } catch (e: Exception) {
            e.printStackTrace()
            isLoading = false
            TextUtils.printTimberLog("got into exception  ${e.message}")
        }


    }

    fun getBusinessListRx(context: Context) {
        isLoading = true
        TextUtils.printTimberLog("got into service")
        val hashMap_daata: HashMap<String, String> = HashMap()
        hashMap_daata["CategoryID"] = catgoryID
        hashMap_daata["AccountID"] = "149871843469177"
        hashMap_daata["Keyword"] = ""
        hashMap_daata["CityID"] = ""
        hashMap_daata["Latitude"] = ""
        hashMap_daata["Longitude"] = ""
        hashMap_daata["PageNo"] = page.toString()
        TextUtils.printTimberLog("got into service 1")

        try {

            apiService?.getBusinessList(hashMap_daata)

            val call: Call<BusinessDataResponse>? = apiService?.getBusinessListNormal(hashMap_daata);
            call?.enqueue(object : Callback<BusinessDataResponse> {
                override fun onFailure(call: Call<BusinessDataResponse>, t: Throwable) {
                    isLoading = false
                    TextUtils.printTimberLog("got into non-server fail  ${t.message}")
                }

                override fun onResponse(call: Call<BusinessDataResponse>, response: Response<BusinessDataResponse>) {
                    val it = response.body()
                    it?.let { categriesModel ->
                        if (categriesModel.Error == null) {
                            TextUtils.printTimberLog("request sucess views ${categriesModel.ListBusinesses?.size}")
                            TextUtils.printTimberLog("request sucess total $categriesModel")

                            categriesModel.ListBusinesses?.forEach {
                                Timber.v("Business_nammmee %s", it.businessName)
                            }

                            categriesModel.ListBusinesses?.let {
                                hasMoreItems = it.isNotEmpty()
                                if (page == 1) {
                                    if (it.isEmpty()){
                                        errorLiveData.postValue("empty")
                                    }

                                    arrayBusiness = it as ArrayList
                                } else {
                                    arrayBusiness.addAll(it as ArrayList)
                                }
                                arrayBusinessLiveData.postValue(categriesModel.ListBusinesses as ArrayList)
                            }

                        } else {
                            TextUtils.printTimberLog("request fail  ${categriesModel.Error}")
                        }
                    }
                    isLoading = false
                }
            })
//            getRepo()?.getBusinessList(hashMap_daata,apiService!!)
//                ?.subscribeOn(Schedulers.io())
//                ?.observeOn(AndroidSchedulers.mainThread())
//                ?.subscribe({
//                    //                    Timber.v("request sucess")
//                    TextUtils.printTimberLog("got into service sucess")
//                    it?.let {categriesModel ->
//                        if (categriesModel.Error == null){
//                            TextUtils.printTimberLog("request sucess views ${categriesModel.ListBusinesses?.size}")
//                            TextUtils.printTimberLog("request sucess total $categriesModel")
//
//                            categriesModel.ListBusinesses?.forEach {
//                                Timber.v("Business_nammmee %s",it.businessName)
//                            }
//
//                            categriesModel.ListBusinesses?.let {
//                                hasMoreItems = it.isNotEmpty()
//                                if (page == 1){
//                                    arrayBusiness = it as ArrayList
//                                }else{
//                                    arrayBusiness.addAll(it as ArrayList)
//                                }
//                                arrayBusinessLiveData.postValue(categriesModel.ListBusinesses as ArrayList)
//                            }
//
//                        }else{
//                            TextUtils.printTimberLog("request fail  ${categriesModel.Error}")
//                        }
//                    }
//                    isLoading = false
//                },{
//                    isLoading = false
//                    TextUtils.printTimberLog("got into non-server fail  ${it.message}")
//                }).let {
//                    it?.let {
//
//                        getCompositeDisposable().add(it)
//                    }
//                }
        } catch (e: Exception) {
            e.printStackTrace()
            isLoading = false
            TextUtils.printTimberLog("got into exception  ${e.message}")
        }


    }

}