package com.m.samplelibb

import android.content.Context
import android.util.Log
import timber.log.Timber

class TextUtils {


    companion object{
        fun initiTimber(){
            if (BuildConfig.DEBUG) {
                Timber.plant(Timber.DebugTree())
            }
        }



        fun printTimberLog(msg : String){
//            Log.v("startfish_log"," "+msg)
           Timber.v("startfish_log %s",msg)
        }

        fun isStringEmptyOrNull(value : String?) : Boolean{
            return value != null && value.trim().isNotEmpty() &&
                    !value.equals("null",ignoreCase = true)
        }
    }



    fun daggerLinkObejct(){
        Log.v("you are giiid"," sdbvdksjbvjk")
    }






}