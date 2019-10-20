package com.yjnull.latte_core.fragment.web;

import android.os.Bundle;

import androidx.annotation.Nullable;

import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.yjnull.latte_core.fragment.web.chromeclient.WebChromeClientImpl;
import com.yjnull.latte_core.fragment.web.client.WebViewClientImpl;
import com.yjnull.latte_core.fragment.web.route.RouteKeys;
import com.yjnull.latte_core.fragment.web.route.Router;

/**
 * Created by zicen on 2018/7/13
 */
public class WebFragmentImpl extends WebFragment {

    private IPageLoadListener mIPageLoadListener = null;

    public static WebFragmentImpl create(String url) {
        final Bundle args = new Bundle();
        args.putString(RouteKeys.URL.name(), url);
        final WebFragmentImpl delegate = new WebFragmentImpl();
        delegate.setArguments(args);
        return delegate;
    }

    public void setPageLoadListener(IPageLoadListener listener) {
        this.mIPageLoadListener = listener;
    }

    @Override
    public Object setLayout() {
        return getWebView();
    }

    @Override
    protected void initView(Bundle savedInstanceState, View rootView) {
        if (getUrl() != null) {
            //用原生的方式模拟Web跳转并进行页面加载
            Router.getInstance().loadPage(this, getUrl());
        }
    }

    @Override
    public IWebViewInitializer setInitializer() {
        return this;
    }

    @Override
    public WebView initWebView(WebView webView) {
        return new WebViewInitializer().createWebView(webView);
    }

    @Override
    public WebViewClient initWebViewClient() {
        final WebViewClientImpl client = new WebViewClientImpl(this);
        client.setPageLoadListener(mIPageLoadListener);
        return client;
    }

    @Override
    public WebChromeClient initWebChromeClient() {
        return new WebChromeClientImpl();
    }

}