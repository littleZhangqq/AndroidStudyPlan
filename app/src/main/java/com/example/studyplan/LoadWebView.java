package com.example.studyplan;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class LoadWebView extends BaseActivity {

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_web_view);
        WebView webView = this.findViewById(R.id.loadwebview);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(request.getUrl().toString());
                return true;
            }
        });
        webView.getSettings().setJavaScriptEnabled(true);
        /*
        * 此处设置url后直接请求会失败，需要先申请网络权限。在mainfest文件中添加如下代码：
        * <uses-permission android:name="android.permission.INTERNET" />
        * <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
        * 之后请求还是可能会失败，因为需要调用HTTPS请求，需要在mainfest中的基础配置项添加：
        *         android:usesCleartextTraffic="true"
        * 之后就可以正常加载webview了
         * */
        webView.loadUrl("http://www.baidu.com/");
    }
}