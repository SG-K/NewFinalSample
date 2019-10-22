package com.m.samplelibb.data

import co.starfish.sharedpreference.PreferenceHelper
import com.m.samplelibb.BuildConfig
import com.m.samplelibb.utils.Constants.ACCEPT
import com.m.samplelibb.utils.Constants.AUTH
import com.m.samplelibb.utils.Constants.CONTENT_TYPE
import com.m.samplelibb.utils.Constants.HOST
import com.m.samplelibb.utils.Constants.LANG
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException


class HeaderInterceptor(private val preferenceHelper: PreferenceHelper) : Interceptor {


    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val requestBuilder = original.newBuilder()

//        requestBuilder.addHeader(AUTH, BuildConfig.securityKey)
//        requestBuilder.addHeader(CONTENT_TYPE, "application/json; charset=utf-8")
//        requestBuilder.addHeader(HOST, BuildConfig.host)
//        requestBuilder.addHeader(ACCEPT, "application/json")
//        requestBuilder.addHeader(LANG, "en")
//        requestBuilder.addHeader(OS_VERSION, Integer.toString(Build.VERSION.SDK_INT))
//        requestBuilder.addHeader(CLIENT_ID, BuildConfig.CLIENT_ID)
//        requestBuilder.addHeader(CLIENT_SECRET, BuildConfig.CLIENT_SECRET)
        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}