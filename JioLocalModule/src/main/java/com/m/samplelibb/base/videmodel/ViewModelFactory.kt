package com.m.samplelibb.base.videmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory<T>(val creator: () -> T) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(BaseViewModel::class.java)) {
//
//        }
//        return SearchViewModel() as T
        return creator() as T
    }
}