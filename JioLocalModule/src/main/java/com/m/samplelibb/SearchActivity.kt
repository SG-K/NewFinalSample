package com.m.samplelibb

import android.app.Activity
import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import com.jakewharton.rxbinding2.view.RxView
import com.m.samplelibb.base.BaseActivity
import com.m.samplelibb.base.videmodel.ViewModelFactory
import com.m.samplelibb.utils.Constants
import kotlinx.android.synthetic.main.activity_lay.*
import java.lang.ref.WeakReference
import java.util.concurrent.TimeUnit

private class SearchActivity : BaseActivity<SearchViewModel>() {
    override fun getInjectViewModel(): SearchViewModel {
        return ViewModelProviders.of(this, ViewModelFactory { SearchViewModel() }).get(SearchViewModel::class.java)
    }

    override fun getLayoutResId(): Int {
        return R.layout.activity_lay
    }

    override fun setupViews() {

        TextUtils.printTimberLog("entered into activity")
        RxView.clicks(btn)
            .throttleFirst(Constants.RX_CLICK_INTERVEL, TimeUnit.MILLISECONDS)
            .subscribe({
                TextUtils.printTimberLog("clicked fragment 1")
                FragmentSearch.newInstance(supportFragmentManager, R.id.frame_container_search_lib)
            }, {

            }).let {
                compositeDisposable.add(it)
            }
    }


    companion object {
        public fun startActivity(activityWeakReference: WeakReference<Activity>) {
            val intent = Intent(activityWeakReference.get(), SearchActivity::class.java)
            activityWeakReference.get()?.startActivity(intent)
        }
    }

}