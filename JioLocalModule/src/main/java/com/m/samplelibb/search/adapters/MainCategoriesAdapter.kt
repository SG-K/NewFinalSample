package com.m.samplelibb.search.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.m.samplelibb.R
import com.m.samplelibb.TextUtils
import com.m.samplelibb.base.BaseViewHolder
import com.m.samplelibb.search.adapters.viewholders.MainCategoryViewHolder
import com.m.samplelibb.search.models.MainCategory
import com.m.samplelibb.utils.KotlinExtensions.calculation
import com.m.samplelibb.utils.KotlinExtensions.screenWidth
import com.m.samplelibb.utils.imageloading.GlideDeligate
import com.m.samplelibb.utils.recyclerview.clicks.AdapterItemClick
import kotlinx.android.synthetic.main.adapter_categories.view.*
import timber.log.Timber

class MainCategoriesAdapter(
    val itemClickCallback: AdapterItemClick<MainCategory>,
    private val glideDelegate: GlideDeligate
) : RecyclerView.Adapter<MainCategoriesAdapter.ViewHolder>() {

    val videosList = ArrayList<MainCategory>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val holder = MainCategoryViewHolder(
//            LayoutInflater.from(parent.context).
//                inflate(R.layout.adapter_categories, parent, false),itemClickCallback,glideDelegate)
//        return holder as BaseViewHolder<Any>
        val layoutInflater = LayoutInflater.from(parent.context)
        val v = layoutInflater.inflate(R.layout.adapter_categories, parent, false)
        return ViewHolder(v, itemClickCallback, 1)
    }

    override fun getItemCount(): Int {
        return videosList.size
    }

    //    override fun onBindViewHolder(holder: BaseViewHolder<Any>, position: Int) {
//        holder.setData(videosList[position])
//    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        videosList?.let {
            TextUtils.printTimberLog("got into track onBindViewHolder")
            holder.onBindView(it[position], position)
        }
    }

    fun setData(list: List<MainCategory>) {
        TextUtils.printTimberLog("got into track 3")
        if (!list.isNullOrEmpty()) {
            TextUtils.printTimberLog("got into track 4 size check ${list.size}")
            videosList.clear()
            videosList.addAll(list)
            notifyDataSetChanged()
        }
    }

    inner class ViewHolder(itemView: View, clickListener: AdapterItemClick<MainCategory>, viewType: Int) :
        RecyclerView.ViewHolder(itemView) {

        init {
//            var width  = ( itemView.context.calculation(32f,
//                itemView.context.screenWidth)).toInt()
//            itemView.layoutParams.width = width
//            itemView.layoutParams.height =  itemView.layoutParams.width

        }

        fun onBindView(onboardingData: MainCategory, position: Int) {
            itemView.setOnClickListener {
                itemClickCallback.onItemClick(adapterPosition, onboardingData)
            }

            itemView.apply {
                Timber.v("startfish_log in imagesadapter %s", onboardingData.Category_Icon)
                Timber.v("startfish_log in imagesadapter %s", onboardingData.CategoryName)
                Timber.v("startfish_log in imagesadapter %s", onboardingData.CategoryID)

                onboardingData.Category_Icon?.let {
                    TextUtils.printTimberLog("got into track 8")
                    glideDelegate.load(im_category,it)
                }
                tv_catogry_name?.text = onboardingData?.CategoryName?:""
            }
        }
    }

}