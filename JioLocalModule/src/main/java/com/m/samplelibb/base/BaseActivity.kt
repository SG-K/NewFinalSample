package com.m.samplelibb.base

import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.m.samplelibb.R
import com.m.samplelibb.utils.Constants.LOG_TAG
import io.reactivex.disposables.CompositeDisposable
//import timber.log.Timber

abstract class BaseActivity <T : BaseViewModel> : AppCompatActivity() {
    lateinit var viewModel: T
    protected var compositeDisposable = CompositeDisposable()

    abstract fun getInjectViewModel(): T

    @LayoutRes
    abstract fun getLayoutResId(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResId())
        viewModel = getInjectViewModel()
        setupViews()
    }

    abstract fun setupViews()

    private fun takeToPlaystore(packageName: String?) {
        try {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$packageName")))
        } catch (e: ActivityNotFoundException) {
            val url = "https://play.google.com/store/apps/details?id=$packageName"
            openUrl(url)
        }

    }

    private fun openUrl(url: String) {
        try {
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(this, "No related activity found", Toast.LENGTH_SHORT).show()
        }

    }


    override fun onDestroy() {
        super.onDestroy()
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
        compositeDisposable.clear()
    }


    override fun onResume() {
        super.onResume()
//        updateNudges()
    }




    fun getStatusBarHeight(): Int {
        var result = 0
        val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            result = resources.getDimensionPixelSize(resourceId)
        }
        return result
    }

    fun setColorOfStatusBar(@ColorRes colorResId: Int) {
//        Timber.v("%s setColorOfStatusBar",LOG_TAG);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (Build.VERSION.SDK_INT >= 19) {
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            }
            setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false)
            window.statusBarColor = ContextCompat.getColor(applicationContext, colorResId)
        }
    }

    fun setUpTransparentStatusBar(semmi_transparent: Boolean,is_white : Boolean) {
        if (Build.VERSION.SDK_INT in 19..20) {
            setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true)
        }
        if (Build.VERSION.SDK_INT >= 19) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }
        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false)
            if (semmi_transparent) {
//                window.statusBarColor = resources.getColor(R.color.black_half)
                window.statusBarColor = Color.parseColor("#55000000")
            } else if (is_white){
                window.statusBarColor = Color.parseColor("#FFFFFF")
            }else {
                window.statusBarColor = resources.getColor(R.color.colorAccent)
            }

        }
    }

    private fun setWindowFlag(bits: Int, on: Boolean) {
        val win = window
        val winParams = win.attributes
        if (on) {
            winParams.flags = winParams.flags or bits
        } else       {
            winParams.flags = winParams.flags and bits.inv()
        }
        win.attributes = winParams
    }
}