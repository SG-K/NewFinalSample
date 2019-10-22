package com.m.samplelibb.search.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.m.samplelibb.R
import com.m.samplelibb.TextUtils
import com.m.samplelibb.base.BaseFragment
import com.m.samplelibb.base.videmodel.ViewModelFactory
import com.m.samplelibb.search.adapters.BusinessListAdapter
import com.m.samplelibb.search.models.BusinessData
import com.m.samplelibb.search.viewmodel.CategorySearchViewModel
import com.m.samplelibb.utils.imageloading.GlideDeligate
import com.m.samplelibb.utils.recyclerview.clicks.AdapterItemClick
import com.m.samplelibb.website.WebisteFragment
import kotlinx.android.synthetic.main.fragment_business.*
import timber.log.Timber

public class FragmentBusinessSearch : BaseFragment<CategorySearchViewModel>() {

    private var layoutManager: LinearLayoutManager? = null
    var mainCategoriesAdapter: BusinessListAdapter? = null


    override fun getInjectViewModel(): CategorySearchViewModel? {
        return activity?.run {
            ViewModelProviders.of(this, ViewModelFactory { CategorySearchViewModel() })
                .get(CategorySearchViewModel::class.java)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun getLayoutId(): Int {

        return R.layout.fragment_business
    }

    var naviagte = false
    override fun initViews() {
        super.initViews()

//        if (!naviagte){
//            naviagte = true
//            return
//        }

        viewModel?.hasMoreItems = true
        viewModel?.isLoading = true
        viewModel?.arrayBusiness?.clear()
        viewModel?.arrayBusinessLiveData?.postValue(null)
        viewModel?.catgoryID = arguments?.getString("id") ?: ""
        viewModel?.page = 1
        loader_main_catgeores?.visibility = View.VISIBLE
        TextUtils.printTimberLog("got into onViewCreated fragment BusinessDat232a")
        observeMainCategories()
        setUpRecyclerview()
        viewModel?.getBusinessList(context!!)

    }


    private fun setUpRecyclerview() {


        recyclerview_main_categories?.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                Timber.v("pagination_exp  scroll")
                val visibleItemCount = layoutManager!!.getChildCount()
                val totalItemCount = layoutManager!!.getItemCount()
                val firstVisibleItemPosition = layoutManager!!.findFirstVisibleItemPosition()

                if (!viewModel!!.isLoading && viewModel!!.hasMoreItems) {
                    if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                        && firstVisibleItemPosition >= 0) {
                        Timber.v("has_more_irem 0 %s", viewModel!!.hasMoreItems)
                        loadMoreItems()
                    }
                }

            }
        })


        layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        recyclerview_main_categories?.layoutManager = layoutManager
        mainCategoriesAdapter = BusinessListAdapter(activity!!, object : AdapterItemClick<BusinessData> {
            override fun onItemClick(viewType: Int, data: BusinessData) {
                val arguments = Bundle()
                arguments.putString("url", data.websiteURL)
                val expertsCommonFragment = WebisteFragment()
                expertsCommonFragment.arguments = arguments
                TextUtils.printTimberLog("got into fragment webiste companioon")


                activity?.supportFragmentManager?.beginTransaction()
                    ?.add(
                        view_id_main, expertsCommonFragment,
                        WebisteFragment::class.java.simpleName
                    )
                    ?.addToBackStack(null)
                    ?.commit()
            }
        }, GlideDeligate(activity!!))
        recyclerview_main_categories?.adapter = mainCategoriesAdapter
    }

    fun observeMainCategories() {
        viewModel?.arrayBusinessLiveData?.observe(this, Observer {
            TextUtils.printTimberLog("got into track 0")
            it?.let {
                TextUtils.printTimberLog("got into track 1")
                loader_main_catgeores?.visibility = View.GONE
                loader_main_catgeores_pagging?.visibility = View.GONE
                mainCategoriesAdapter?.setData(it,viewModel?.page?.toString()?:"1")

            }
        })

        viewModel?.errorLiveData?.observe(this, Observer {
            TextUtils.printTimberLog("got into track 0")
            it?.let {
                TextUtils.printTimberLog("got into track 1")
                viewModel?.errorLiveData?.postValue(null)
                loader_main_catgeores?.visibility = View.GONE
                loader_main_catgeores_pagging?.visibility = View.GONE
                tv_no_results?.visibility = View.VISIBLE
            }
        })
    }

    companion object {
        var view_id_main: Int = -1
        fun newInstance(
            supportFragmentManagerinstance: FragmentManager?,
            view_id: Int, categoryid: String) {

            val arguments = Bundle()
            view_id_main = view_id
            arguments.putString("id", categoryid)

            val expertsCommonFragment = FragmentBusinessSearch()
            expertsCommonFragment.arguments = arguments
            supportFragmentManagerinstance?.beginTransaction()
                ?.add(
                    view_id, expertsCommonFragment,
                    FragmentBusinessSearch::class.java.simpleName
                )
                ?.addToBackStack( FragmentBusinessSearch::class.java.simpleName)
                ?.commit()
        }
    }

    /**
     * on paggination invoke loading more items by increasing page number
     */
    private fun loadMoreItems() {
        Timber.v("pagination_exp  got_moreitems")
        if (viewModel?.hasMoreItems == true) {
            viewModel!!.page++
            loader_main_catgeores_pagging?.visibility = View.VISIBLE
            viewModel?.getBusinessList(context!! )
        }
    }

}