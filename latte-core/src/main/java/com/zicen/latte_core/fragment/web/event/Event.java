package com.zicen.latte_core.fragment.web.event;

import android.content.Context;
import android.webkit.WebView;

import com.zicen.latte_core.fragment.web.WebFragment;

import me.yokeyword.fragmentation.ISupportFragment;

/**
 * Created by zicen on 2018/7/13
 */
public abstract class Event implements IEvent {

    private Context mContent = null;
    private String mAction = null;
    private WebFragment mFragment = null;
    private String mUrl = null;
    private WebView mWebView = null;

    public WebView getWebView(){
        return mFragment.getWebView();
    }

    public Context getContext() {
        return mContent;
    }

    public void setContext(Context mContent) {
        this.mContent = mContent;
    }

    public String getAction() {
        return mAction;
    }

    public void setAction(String mAction) {
        this.mAction = mAction;
    }

    public ISupportFragment getFragment() {
        return mFragment;
    }

    public void setFragment(WebFragment mFragment) {
        this.mFragment = mFragment;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String mUrl) {
        this.mUrl = mUrl;
    }
}
