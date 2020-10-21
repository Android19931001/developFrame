package com.develop.frame.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.develop.frame.R;
import com.develop.frame.base.BaseActivity;

public class WebViewActivity extends BaseActivity {

    //    private WebView webView;
    private TextView tvWebTitle;
    private String webTitle, webUrl;

    private static final String WEB_TITLE = "TITLE";
    private static final String WEB_URL = "URL";

    public static void goWebView(Context context, String title, String url) {
        context.startActivity(new Intent(context, WebViewActivity.class)
                .putExtra(WEB_TITLE, title)
                .putExtra(WEB_URL, url));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        immersionBar.statusBarColor(R.color.color_87CEFF).navigationBarColor(R.color.black_color).init();
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public void initView() {
//        webView = findView(R.id.webView);
//        tvWebTitle = findView(R.id.tv_title);
    }

    @Override
    public void initData() {
        webTitle = getIntent().getStringExtra(WEB_TITLE);
        webUrl = getIntent().getStringExtra(WEB_URL);
//        tvWebTitle.setText(webTitle);
//        initWebView();
//        webView.loadUrl(webUrl);
    }


    /**
     * 配置WebView
     */
    private void initWebView() {
//        WebSettings settings = webView.getSettings();
//        settings.setSupportZoom(false);//支持缩放
//        settings.setJavaScriptEnabled(true);//支持javaScript
//        settings.setGeolocationEnabled(true);//打开位置定位
//        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);//不使用缓存，只从网络获取数据.
//        settings.setDomStorageEnabled(true); // 开启 DOM storage API 功能
//        settings.setDatabaseEnabled(true);   //开启 database storage API 功能
//        settings.setAppCacheEnabled(true);//开启 Application Caches 功能
//        adapterWebView(settings);
//        webView.requestFocus();
//        webView.setWebChromeClient(webChromeClient);
//        webView.setWebViewClient(webViewClient);



    }

    /**
     * 适配网页
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
     * 使用自己的浏览器浏览不使用手机的第三方加载网页
     */
    private WebChromeClient webChromeClient = new WebChromeClient() {
        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
//            tvWebTitle.setText(title);
        }
    };

    /**
     * webView端
     */
    private WebViewClient webViewClient = new WebViewClient() {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
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
     * 控制内存泄漏
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
//        if (webView != null) {
//            webView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
//            webView.clearHistory();
//            ((ViewGroup) webView.getParent()).removeView(webView);
//            webView.destroy();
//            webView = null;
//        }
    }
}
