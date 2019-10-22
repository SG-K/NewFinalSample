package com.m.samplelibb.search.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.m.samplelibb.R
import com.m.samplelibb.TextUtils
import com.m.samplelibb.base.BaseFragment
import com.m.samplelibb.base.videmodel.ViewModelFactory
import com.m.samplelibb.search.adapters.MainCategoriesAdapter
import com.m.samplelibb.search.models.MainCategory
import com.m.samplelibb.search.viewmodel.CategorySearchViewModel
import com.m.samplelibb.utils.imageloading.GlideDeligate
import com.m.samplelibb.utils.recyclerview.clicks.AdapterItemClick
import kotlinx.android.synthetic.main.fragment_main_categories.*

class FragmentCategorySearch : BaseFragment<CategorySearchViewModel>() {

    var mainCategoriesAdapter : MainCategoriesAdapter? = null

    override fun getInjectViewModel(): CategorySearchViewModel? {
        return activity?.run {
            ViewModelProviders.of(this, ViewModelFactory { CategorySearchViewModel() }).get(CategorySearchViewModel::class.java)
        }

    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_main_categories
    }

    override fun initViews() {
        super.initViews()
        TextUtils.initiTimber()
        TextUtils.printTimberLog("got into fragment MainCategory")
        observeMainCategories()
        setUpRecyclerview()
        viewModel?.getResults(activity!!)
    }

    private fun setUpRecyclerview() {
        recyclerview_main_categories?.layoutManager = GridLayoutManager(activity, 3)
        mainCategoriesAdapter = MainCategoriesAdapter(object : AdapterItemClick<MainCategory> {
            override fun onItemClick(viewType: Int, data: MainCategory) {
                TextUtils.printTimberLog("got sdvbjsdbvhb into track 0")
                FragmentBusinessSearch.newInstance(activity!!.supportFragmentManager, view_id_main,data.CategoryID?:"")
            }
        }, GlideDeligate(activity!!))
        recyclerview_main_categories?.adapter = mainCategoriesAdapter
    }

    fun observeMainCategories(){
        viewModel?.arrayCategoriesLiveData?.observe(activity!!, Observer {
            TextUtils.printTimberLog("got into track 0")
            it?.let {
                TextUtils.printTimberLog("got into track 1")
                loader_main_catgeores?.visibility = View.GONE
                mainCategoriesAdapter?.setData(it)

            }
        })
    }

    companion object {
        var view_id_main : Int= -1
        fun newInstance(supportFragmentManagerinstance: FragmentManager?,
                        view_id: Int) {
            val arguments = Bundle()
            view_id_main = view_id
            arguments.putInt("id", view_id)
            val expertsCommonFragment = FragmentCategorySearch()
            expertsCommonFragment.arguments = arguments

            supportFragmentManagerinstance?.beginTransaction()
                ?.add(view_id, expertsCommonFragment,
                    FragmentCategorySearch::class.java.simpleName)
//                ?.addToBackStack(FragmentCategorySearch::class.java.simpleName)
                ?.commit()
        }
    }

}