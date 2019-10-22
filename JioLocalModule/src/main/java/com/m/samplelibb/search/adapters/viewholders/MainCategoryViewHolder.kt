package com.m.samplelibb.search.adapters.viewholders

import android.view.View
import androidx.core.content.ContextCompat
import com.m.samplelibb.R
import com.m.samplelibb.TextUtils
import com.m.samplelibb.base.BaseViewHolder
import com.m.samplelibb.search.models.MainCategory
import com.m.samplelibb.utils.imageloading.GlideDeligate
import com.m.samplelibb.utils.recyclerview.clicks.AdapterItemClick
import kotlinx.android.synthetic.main.adapter_categories.view.*

class MainCategoryViewHolder(view: View, private val itemClickCallback: AdapterItemClick<MainCategory>,
                             private val glideDelegate: GlideDeligate
) : BaseViewHolder<MainCategory>(view){


    init {
        TextUtils.printTimberLog("got into track 9")
        itemView.setOnClickListener {
            dataModel?.let {
                itemClickCallback.onItemClick(itemViewType,dataModel!!)
            }
        }
    }
    override fun setData(data: MainCategory) {
        super.setData(data)
        TextUtils.printTimberLog("got into track 6")
        itemView.apply {
            TextUtils.printTimberLog("got into track 7 ${data.Category_Icon}")
            data.Category_Icon?.let {
                TextUtils.printTimberLog("got into track 8")
                glideDelegate.load(im_category,it)
            }
        }
    }
}
