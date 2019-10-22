package com.m.samplelibb.search.adapters

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.m.samplelibb.R
import com.m.samplelibb.TextUtils
import com.m.samplelibb.search.models.BusinessData
import com.m.samplelibb.utils.KotlinExtensions.calculation
import com.m.samplelibb.utils.KotlinExtensions.screenWidth
import com.m.samplelibb.utils.imageloading.GlideDeligate
import com.m.samplelibb.utils.recyclerview.clicks.AdapterItemClick
import kotlinx.android.synthetic.main.adapter_business_list.view.*
import kotlinx.android.synthetic.main.adapter_categories.view.*
import timber.log.Timber

class BusinessListAdapter(
   val context: Context, val itemClickCallback: AdapterItemClick<BusinessData>,
    private val glideDelegate: GlideDeligate
) : RecyclerView.Adapter<BusinessListAdapter.ViewHolder>() {

    val videosList = ArrayList<BusinessData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val holder = BusinessDataViewHolder(
//            LayoutInflater.from(parent.context).
//                inflate(R.layout.adapter_categories, parent, false),itemClickCallback,glideDelegate)
//        return holder as BaseViewHolder<Any>
        val layoutInflater = LayoutInflater.from(parent.context)
        val v = layoutInflater.inflate(R.layout.adapter_business_list, parent, false)
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
            holder.onBindView(it[position], position)
        }
    }

    fun setData(list: List<BusinessData>, page : String) {
        if (!list.isNullOrEmpty()) {
            if (page.equals("1",ignoreCase = true)){
                videosList.clear()
            }
            videosList.addAll(list)
            notifyDataSetChanged()
        }
    }


    inner class ViewHolder(itemView: View, clickListener: AdapterItemClick<BusinessData>, viewType: Int) :
        RecyclerView.ViewHolder(itemView) {

        init {


        }

        fun onBindView(onboardingData: BusinessData, position: Int) {
            itemView.setOnClickListener {
                itemClickCallback.onItemClick(adapterPosition, onboardingData)
            }

            itemView.apply {
                onboardingData.imageID?.let {
                    TextUtils.printTimberLog("got into track 8")
                    glideDelegate.load(im_bussines_logo,it)
                }
                tv_business_name?.text = onboardingData?.businessName?:""
                tv_business_addrs?.text = onboardingData?.address?:""

                if (TextUtils.isStringEmptyOrNull(onboardingData?.rating)){
                    tv_rating?.text ="${onboardingData?.rating?:""} "
                }else{
                    tv_rating?.text =""
                }

                if (TextUtils.isStringEmptyOrNull(onboardingData?.reviews)){
                    tv_rating_count?.text =" ("+ onboardingData?.reviews+")"?:""
                }else{
                    tv_rating_count?.text =""

                }

                if (TextUtils.isStringEmptyOrNull(onboardingData.distance)){
                    tv_distance?.text ="  |  ${onboardingData?.distance?:""}km"
                }else{
                    tv_distance?.text =""
                }



                rl_call?.setOnClickListener {
                    val uri = Uri.parse("tel:+${onboardingData.mobileNo}")
                    val intent = Intent(Intent.ACTION_DIAL, uri)
                    context?.startActivity(intent)
                }

            }
        }
    }

}