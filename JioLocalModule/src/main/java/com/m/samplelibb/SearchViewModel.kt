package com.m.samplelibb

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.m.samplelibb.base.BaseViewModel
import com.m.samplelibb.models.ListCardsItem
import com.m.samplelibb.utils.Constants.LOG_TAG
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
//import timber.log.Timber
import java.lang.Exception

class SearchViewModel  constructor(

) : BaseViewModel() {

    var colorWhite : String = "W"
    var colorAccent : String = "A"

    var filteredTvShowsLiveData = MutableLiveData<ArrayList<ListCardsItem>>()

    fun getResults(context: Context){
        TextUtils.printTimberLog("got into service")
        val hashMap_daata : HashMap<String,String> = HashMap()
        hashMap_daata["AccountID"] = "168946779229808"
        hashMap_daata["BusinessID"] = "238176711548042"
        hashMap_daata["PageNumber"] = "1"
        TextUtils.printTimberLog("got into service 1")

        try {
            val view_moshi = getMoshi()

            TextUtils.printTimberLog("got into service 2")

            val predds = providePreferenceHelper(context)

            TextUtils.printTimberLog("got into service 3")

            val okhhbbjs = provideOkHttpClient(
                predds,view_moshi)

            TextUtils.printTimberLog("got into service 4")

            val retrojfknfv = provideRetrofit(okhhbbjs,view_moshi)

            TextUtils.printTimberLog("got into service 5")

            val apijkdvnkj = provideApiService(retrojfknfv)

            TextUtils.printTimberLog("got into service 6")

            TextUtils.printTimberLog("got into service 7")
            getRepo()?.getDashboard(hashMap_daata,apijkdvnkj)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe({
//                    Timber.v("request sucess")
                    TextUtils.printTimberLog("got into service sucess")
                    it?.let {dashboardModel ->
                        if (dashboardModel.error == null){
                            Log.v("Start_fish_log","views sucess "+dashboardModel.listCards?.size)
//                            Timber.v("%s request sucess views %s",LOG_TAG,dashboardModel.listCards?.size)
//                            Timber.v("%s request sucess total %s",LOG_TAG,dashboardModel)
                            filteredTvShowsLiveData.postValue(dashboardModel.listCards as ArrayList)
                        }else{
                            Log.v("Start_fish_log","views fail "+dashboardModel.error)
//                            Timber.v("%s request fail %s",LOG_TAG,dashboardModel.error);
                        }
                    }
                },{
                    TextUtils.printTimberLog("got into service error")
//                    Timber.v("request fail %s",it.message)
                }).let {
                    it?.let {
                        getCompositeDisposable().add(it)
                    }
                }
        }catch (e :Exception){
            e.printStackTrace()
            TextUtils.printTimberLog("got into service 9 exception ${e.message}")
        }


//        val fgg = provideApiService(
//            provideRetrofit(provideOkHttpClient(
//                providePreferenceHelper(context),getMoshi()),
//                getMoshi()))

    }






}