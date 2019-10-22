package com.m.samplelibb.utils

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.util.Log
import androidx.appcompat.widget.AppCompatTextView
import com.m.samplelibb.R
import java.util.*

class TextViewPlus : AppCompatTextView {

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        setCustomFont(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        setCustomFont(context, attrs)
    }

    private fun setCustomFont(ctx: Context, attrs: AttributeSet) {
        val a = ctx.obtainStyledAttributes(attrs, R.styleable.TextViewPlus)
        val customFont = a.getString(R.styleable.TextViewPlus_customFont)

        if (customFont != null) {
            setCustomFont(ctx, customFont)
        }

        a.recycle()
    }

    fun setCustomFont(ctx: Context, font: String): Boolean {
        var tf = sCachedTypeface[font]

        if (tf == null) {
            try {
                tf = Typeface.createFromAsset(ctx.assets, font)
                sCachedTypeface[font] = tf
            } catch (e: Throwable) {
                Log.e(TAG, "Could not get typeface: " + e.message)
                return false
            }

        }

        typeface = tf

        return true
    }

    companion object {

        private val TAG = "TextViewPlus"
        private val sCachedTypeface = HashMap<String, Typeface>()
    }

}

