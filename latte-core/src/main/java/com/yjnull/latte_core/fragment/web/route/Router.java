package com.yjnull.latte_core.fragment.web.route;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import androidx.core.content.ContextCompat;
import me.yokeyword.fragmentation.ISupportFragment;

import android.text.TextUtils;
import android.webkit.URLUtil;
import android.webkit.WebView;

import com.yjnull.latte_core.fragment.web.WebFragment;
import com.yjnull.latte_core.fragment.web.WebFragmentImpl;

/**
 * Created by zicen on 2018/7/13
 */
public class Router {

    private Router() {
    }

    private static class Holder{
        private static final Router INSTANCE = new Router();
    }

    public static Router getInstance() { return Holder.INSTANCE; }

    public final boolean handleWebUrl(WebFragment delegate, String url) {

        if (!TextUtils.isEmpty(url) && url.contains("tel:")) {
            callPhone(delegate.getContext(), url);
            return true;
        }

//        final ISupportFragment topDelegate = delegate.getTopDelegate();
//
//        final WebFragmentImpl webDelegate = WebFragmentImpl.create(url);
//        topDelegate.getSupportDelegate().start(webDelegate);

        return true;
    }

    private void loadWebPage(WebView webView, String url) {
        if (webView == null) {
            throw new NullPointerException("WebView is null!");
        } else {
            webView.loadUrl(url);
        }
    }

    private void loadLocalPage(WebView webView, String url) {
        loadWebPage(webView, "file:///android_asset/" + url);
    }

    private void loadPage(WebView webView, String url) {
        if (URLUtil.isNetworkUrl(url) || URLUtil.isAssetUrl(url)) {
            loadWebPage(webView, url);
        } else {
            loadLocalPage(webView, url);
        }
    }

    public final void loadPage(WebFragment delegate, String url) {
        loadPage(delegate.getWebView(), url);
    }

    private void callPhone(Context context, String uri) {
        final Intent intent = new Intent(Intent.ACTION_DIAL);
        final Uri data = Uri.parse(uri);
        intent.setData(data);
        ContextCompat.startActivity(context, intent, null);
    }
}
