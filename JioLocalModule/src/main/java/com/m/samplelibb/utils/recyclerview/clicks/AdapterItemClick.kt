package com.m.samplelibb.utils.recyclerview.clicks

interface AdapterItemClick <T> {

    fun onItemClick(viewType: Int, data:T)
}