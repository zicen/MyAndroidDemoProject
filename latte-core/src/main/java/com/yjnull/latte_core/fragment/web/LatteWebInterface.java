package com.yjnull.latte_core.fragment.web;

import android.webkit.JavascriptInterface;

import com.alibaba.fastjson.JSON;
import com.yjnull.latte_core.fragment.web.event.Event;
import com.yjnull.latte_core.fragment.web.event.EventManager;

/**
 * Created by zicen on 2018/7/13
 * 用来和原生进行交互的
 */
final class LatteWebInterface {

    private final WebFragment FRAGMENT;

    private LatteWebInterface(WebFragment fragment) {
        this.FRAGMENT = fragment;
    }

    static LatteWebInterface create(WebFragment fragment) {
        return new LatteWebInterface(fragment);
    }

    @SuppressWarnings("unused")
    @JavascriptInterface
    public String event(String params) {
        final String action = JSON.parseObject(params).getString("action");
        final Event event = EventManager.getInstance().createEvent(action);
        if (event != null) {
            event.setAction(action);
            event.setFragment(FRAGMENT);
            event.setContext(FRAGMENT.getContext());
            event.setUrl(FRAGMENT.getUrl());
            return event.execute(params);
        }
        return null;
    }
}
