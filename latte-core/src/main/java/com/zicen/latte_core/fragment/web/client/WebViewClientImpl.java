package com.zicen.latte_core.fragment.web.client;

import android.graphics.Bitmap;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.zicen.latte_core.fragment.web.IPageLoadListener;
import com.zicen.latte_core.fragment.web.WebFragment;
import com.zicen.latte_core.fragment.web.route.Router;
import com.zicen.latte_core.ui.loader.LatteLoader;

/**
 * Created by zicen on 2018/7/13
 */
public class WebViewClientImpl extends WebViewClient {

    private final WebFragment FRAGMENT;
    private IPageLoadListener mIPageLoadListener = null;

    public void setPageLoadListener(IPageLoadListener listener) {
        this.mIPageLoadListener = listener;
    }

    public WebViewClientImpl(WebFragment fragment) {
        this.FRAGMENT = fragment;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        //false 由webview处理事件, true 由我们处理事件
        return Router.getInstance().handleWebUrl(FRAGMENT, url);
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        if (mIPageLoadListener != null) {
            mIPageLoadListener.onLoadStart();
        }
        LatteLoader.showLoading(view.getContext());
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        if (mIPageLoadListener != null) {
            mIPageLoadListener.onLoadEnd();
        }
        LatteLoader.stopLoading();
    }
}
