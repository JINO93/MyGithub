package com.jino.mygithub.feature.login

import android.graphics.Bitmap
import android.os.Bundle
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.viewModels
import com.jino.mygithub.BuildConfig
import com.jino.mygithub.R
import com.jino.mygithub.base.BaseActivity
import com.jino.mygithub.databinding.ActivityLoginBinding
import com.jino.mygithub.extend.gone
import com.jino.mygithub.extend.viewbinding.bindViews
import com.jino.mygithub.extend.visibleIf
import com.jino.mygithub.util.LogUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginWebActivity : BaseActivity() {

    companion object {
        const val AUTHED_URL = "jinogithub://authed"
    }

    private val mViewModel by viewModels<LoginViewModel>()
    private val mViewBinding by bindViews<ActivityLoginBinding>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    override fun initListener() {
        mViewModel.loadStatus.observe(this){
            mViewBinding.oauthWebviewLoadingBar.visibleIf { it.data?:false }
        }

        mViewModel.loginSuccess.observe(this){
            if(it){
                setResult(LoginActivity.RESULT_SUCCESS)
            }else{
                setResult(LoginActivity.RESULT_FAILED)
            }
            finish()
        }
    }

    override fun initView() {
        with(mViewBinding.oauthWebview) {
            settings.also {
                it.javaScriptEnabled = true
                it.loadWithOverviewMode = true
                it.builtInZoomControls = false
                it.displayZoomControls = false
                it.domStorageEnabled = true
                it.layoutAlgorithm = WebSettings.LayoutAlgorithm.NARROW_COLUMNS
                it.setAppCacheEnabled(true)
            }
            webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(
                    view: WebView?,
                    request: WebResourceRequest?
                ): Boolean {
                    LogUtils.d("JINO","shouldOverrideUrlLoading  url:${request?.url}")
                    if (request != null && request.url != null &&
                        request.url.toString().startsWith(AUTHED_URL)
                    ) {
                        val code = request.url.getQueryParameter("code")
                        if (code != null) {
                            mViewModel.oauth(context!!, code)
                        }
                        return true
                    }
                    return false
                }

                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                    super.onPageStarted(view, url, favicon)
                    LogUtils.d("JINO","onPageStarted  url:$url")
                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    mViewBinding.oauthWebviewLoadingBar.gone()
                }
            }

//            val url = "https://github.com/login/oauth/authorize?" +
//                    "client_id=${BuildConfig.CLIENT_ID}&" +
//                    "state=app&redirect_uri=$AUTHED_URL"
            val url = "https://www.baidu.com"
            LogUtils.d("JINO","load  url:$url")
            loadUrl(url)

        }
    }

    override fun setupToolBar() {
    }
}