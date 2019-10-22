package com.m.samplelibb.base

import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.m.samplelibb.utils.recyclerview.clicks.AdapterItemClick

abstract class BaseViewHolder<T>(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener{

    var dataModel: T? = null

    var adapterCallback: AdapterItemClick<T>?=null

    open fun setData(data: T){
        dataModel = data
    }

    fun setCallbackListener(callback: AdapterItemClick<T>?){
        adapterCallback = callback
    }


    override fun onClick(clickedView: View?) {

    }
}