package com.m.samplelibb.utils.imageloading

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class GlideDeligate constructor(context: Context) {

    private var context: Context? = null

    init {
        this.context = context.applicationContext
    }

    fun load(imageView: ImageView, imageUrl: String) {
        context?.let {
            Glide.with(it)
                .load(imageUrl)
                .into(imageView)
        }
    }

    fun loadWithCenterCrop(imageView: ImageView, imageUrl: String) {
        context?.let {
            val myOptions = RequestOptions()
                .centerCrop()
            Glide.with(it)
                .load(imageUrl)
                .apply(myOptions)
                .into(imageView)
        }
    }

    fun loadWithCenterCropCircler(imageView: ImageView, imageUrl: String) {
        context?.let {
            val myOptions = RequestOptions()
                .centerCrop()
                .optionalCircleCrop()
            Glide.with(it)
                .load(imageUrl)
                .apply(myOptions)
                .into(imageView)
        }
    }

}