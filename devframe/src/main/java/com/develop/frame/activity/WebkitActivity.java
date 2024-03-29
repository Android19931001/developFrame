package com.develop.frame.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.develop.frame.R;
import com.develop.frame.base.BaseActivity;
import com.develop.frame.databinding.ActivityWebkitBinding;
import com.develop.frame.utils.ILog;

import androidx.databinding.DataBindingUtil;

public class WebkitActivity extends BaseActivity {

    public static final String WEB_TITLE = "WEB_TITLE";
    public static final String WEB_URL = "WEB_URL";

    private ActivityWebkitBinding webkitBinding;

    public static Intent toIntent(Context context, String title, String url) {
        return new Intent(context, WebkitActivity.class)
                .putExtra(WEB_TITLE, title)
                .putExtra(WEB_URL, url);
    }

    private String webUrl, webTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        webkitBinding = DataBindingUtil.setContentView(this, R.layout.activity_webkit);
        immersionBar.statusBarColor(R.color.color_87CEFF).navigationBarColor(R.color.black_color).init();

    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public void initView() {
        initWebView();

    }

    @Override
    public void initData() {
        webTitle = getIntent().getStringExtra(WEB_TITLE);
        webUrl = getIntent().getStringExtra(WEB_URL);
        webkitBinding.topView.tvTitleFrameCenter.setText(webTitle);
        webkitBinding.webView.loadUrl(webUrl);
    }


    /**
     * init webview settings
     */
    private void initWebView() {
        WebSettings settings = webkitBinding.webView.getSettings();
        settings.setSupportZoom(false);//支持缩放
        settings.setJavaScriptEnabled(true);//支持javaScript
        settings.setGeolocationEnabled(true);//打开位置定位
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);//不使用缓存，只从网络获取数据.
        settings.setDomStorageEnabled(true); // 开启 DOM storage API 功能
        settings.setDatabaseEnabled(true);   //开启 database storage API 功能
        settings.setAppCacheEnabled(true);//开启 Application Caches 功能
        adapterWebView(settings);
        webkitBinding.webView.requestFocus();
        webkitBinding.webView.setWebChromeClient(webChromeClient);
        webkitBinding.webView.setWebViewClient(webViewClient);

    }

    /**
     * adapter web
     */
    private void adapterWebView(WebSettings settings) {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int mDensity = metrics.densityDpi;

        if (mDensity == 120) {
            settings.setDefaultZoom(WebSettings.ZoomDensity.CLOSE);
        } else if (mDensity == 160) {
            settings.setDefaultZoom(WebSettings.ZoomDensity.MEDIUM);
        } else if (mDensity == 240) {
            settings.setDefaultZoom(WebSettings.ZoomDensity.FAR);
        }
    }


    /**
     * Using local browser to load web
     */
    private WebChromeClient webChromeClient = new WebChromeClient() {
        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            webkitBinding.topView.tvTitleFrameCenter.setText(title);
        }


        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            if (100 == newProgress) {

            } else {

            }
        }
    };

    /**
     * webViewClient's callback
     */
    private WebViewClient webViewClient = new WebViewClient() {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            ILog.e(url);
            try {
                if (url.startsWith("androidamap")) {//跳转到高德地图导航
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(url));
                    startActivity(intent);
                    return true;
                } else if (url.startsWith("alipay")) {//支付宝支付
                    Intent intent = Intent.parseUri(url,
                            Intent.URI_INTENT_SCHEME);
                    intent.addCategory("android.intent.category.BROWSABLE");
                    intent.setComponent(null);
                    startActivity(intent);
                    return true;
                } else if (url.startsWith("weixin")) {
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(url));
                    startActivity(intent);
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return super.shouldOverrideUrlLoading(view, url);
        }
    };


    /**
     * control memory leak
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (webkitBinding.webView != null) {
            webkitBinding.webView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            webkitBinding.webView.clearHistory();
            ((ViewGroup) webkitBinding.webView.getParent()).removeView(webkitBinding.webView);
            webkitBinding.webView.destroy();
        }
    }
}
