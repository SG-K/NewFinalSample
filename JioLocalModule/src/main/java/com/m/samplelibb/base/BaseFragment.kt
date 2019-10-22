package com.m.samplelibb.base

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.m.samplelibb.TextUtils
import io.reactivex.disposables.CompositeDisposable

abstract class BaseFragment <VM : BaseViewModel> : Fragment() {
    protected var viewModel: VM? = null
    protected val compositeDisposable = CompositeDisposable()
    private var mActivity: BaseActivity<*>? = null

    @LayoutRes
    abstract fun getLayoutId(): Int

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.viewModel = getInjectViewModel()
        if (context is BaseActivity<*>) {
            this.mActivity = context
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayoutId(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        TextUtils.printTimberLog("got into fragment onViewCreated")
        initViews()
    }

    abstract fun getInjectViewModel(): VM?

    fun isViewLive() = isAdded && view != null

    protected open fun initViews() {

    }

    override fun onDetach() {

        mActivity = null
        viewModel?.onCleared()
        compositeDisposable.clear()
        super.onDetach()
    }

    fun getBaseActivity(): BaseActivity<*>? {
        return mActivity
    }






}