package com.ankit.trendinggit.view.ui.repodetail

import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.ankit.trendinggit.R
import kotlinx.android.synthetic.main.fragment_repo_detail.*
import org.jetbrains.anko.sdk27.coroutines.onClick


class RepoDetailFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_repo_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val url = arguments?.let { RepoDetailFragmentArgs.fromBundle(it).url }

        setupWebView()
        setClickListeners()

        repo_web_view.loadUrl(url)
    }

    private fun setClickListeners() {
        repo_back_button.onClick {
            repo_web_view.goBack()
        }

        repo_forward_button.onClick {
            repo_web_view.goForward()
        }

        repo_refresh_button.onClick {
            repo_web_view.reload()
        }
    }

    private fun setupWebView() {
        repo_web_view.setInitialScale(1)
        val webSettings = repo_web_view.settings
        webSettings.setAppCacheEnabled(false)
        webSettings.builtInZoomControls = true
        webSettings.displayZoomControls = false
        webSettings.javaScriptEnabled = true
        webSettings.useWideViewPort = true
        webSettings.domStorageEnabled = true

        repo_web_view.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                if (repo_back_button != null && repo_forward_button != null && repo_web_view != null && repo_progress_view != null) {
                    repo_back_button.isEnabled = repo_web_view.canGoBack()
                    repo_forward_button.isEnabled = repo_web_view.canGoForward()
                    repo_progress_view.visibility = View.VISIBLE
                }
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                if (repo_back_button != null && repo_forward_button != null && repo_web_view != null && repo_progress_view != null) {
                    repo_back_button.isEnabled = repo_web_view.canGoBack()
                    repo_forward_button.isEnabled = repo_web_view.canGoForward()
                    repo_progress_view.visibility = View.GONE
                }
            }
        }
    }
}