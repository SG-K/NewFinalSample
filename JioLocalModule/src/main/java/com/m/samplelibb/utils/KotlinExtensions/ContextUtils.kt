package com.m.samplelibb.utils.KotlinExtensions

import android.app.Activity
import android.content.Context
import android.os.Build
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.ColorRes
import java.lang.ref.WeakReference
import java.util.regex.Pattern

fun Context.toast(msg: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, msg, duration).show()
}

/**
 * Value is returned pixel
 */
val Context.screenWidth: Int
    get() = resources.displayMetrics.widthPixels

val Context.screenHeight: Int
    get() = resources.displayMetrics.heightPixels

//calculate aspect ratio with percentage
fun Context.calculation(percent: Float, total: Int): Double {
    var final_value = 0.0
    final_value = (percent / 100 * total).toDouble()
    return final_value

}

fun Context.setSatatusBarColor(context: WeakReference<Activity>, @ColorRes colorResId: Int) {

    if (Build.VERSION.SDK_INT >= 21) {
        val window = context.get()?.window
        window?.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window?.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window?.statusBarColor = context?.get()!!.resources.getColor(colorResId)
    }

}

fun Context.getPxInDp(px: Int): Int {
    val displayMetrics = resources.displayMetrics
    return Math.round(px / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT))

}
val EMAIL_ADDRESS_PATTERN = Pattern
        .compile("[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" + "\\@"
                + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" + "(" + "\\."
                + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" + ")+")
fun Context.checkEmail(email: String): Boolean {
    return EMAIL_ADDRESS_PATTERN.matcher(email).matches()
}

fun Context.printLog(lable: String,msg: String) {
    Log.d(lable, msg)
}

fun Context.showToast(msg: String,length : Int) {
    Toast.makeText(this, msg,
            Toast.LENGTH_SHORT).show()
}

fun Context.imageScaling(width2: Int, image_h: Int, image_w: Int): Int {
    var h = image_h
    h = h * width2 / image_w
    return h
}

//fun Context.hasNetwork(): Boolean? {
//    var isConnected: Boolean? = false // Initial Value
//    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//    val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
//    if (activeNetwork != null && activeNetwork.isConnected)
//        isConnected = true
//    return isConnected
//}

fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}

fun View.showSoftKeyboard(view2: View?) {
    if (view2 != null) {
        view2!!.requestFocus()
        val manager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        // manager.showSoftInput(view, InputMethodManager.SHOW_FORCED);

        manager.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)

    }
}