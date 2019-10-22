package com.m.samplelibb.website

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.m.samplelibb.R
import kotlinx.android.synthetic.main.fragment_website.*

public class WebisteFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_website, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getString("url")?.let {
            webiste_webview?.loadUrl(it)
        }
        setWebviewPrpps()
    }

    private fun setWebviewPrpps() {

        // region webview permissions and settings */
        webiste_webview?.getSettings()?.setJavaScriptEnabled(true)
        webiste_webview?.getSettings()?.setDomStorageEnabled(true)


//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            webiste_webview?.getSettings()?.setDomStorageEnabled(true);
//        }


        webiste_webview?.getSettings()?.setLoadWithOverviewMode(true)
        webiste_webview?.getSettings()?.setUseWideViewPort(true)

        webiste_webview?.getSettings()?.setSupportZoom(true)
        webiste_webview?.getSettings()?.setBuiltInZoomControls(true)
        webiste_webview?.getSettings()?.setDisplayZoomControls(false)

        webiste_webview?.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY)
        webiste_webview?.setScrollbarFadingEnabled(false)
        
        webiste_webview?.webViewClient = object : WebViewClient() {

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                loader_main_webiste?.visibility = View.VISIBLE
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                try {
                    loader_main_webiste?.visibility = View.GONE
                } catch (e: Exception) {
                }
            }

            override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
                super.onReceivedError(view, request, error)
                loader_main_webiste?.visibility = View.GONE
            }

        }
    }




}