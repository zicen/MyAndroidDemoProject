package com.zicen.myandroiddemoproject;

import android.app.Application;

import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.yjnull.latte_core.app.Latte;
import com.yjnull.latte_core.net.interceptors.DebugInterceptor;


public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .withIcon(new FontAwesomeModule())
                .withLoaderDelayed(100)
                .withApiHost("http://www.wanandroid.com/")
//                .withInterceptor(new DebugInterceptor("order_list", R.raw.order_list))
                .withWeChatAppId("")
                .withWeChatAppSecret("")
                .withJavascriptInterface("latte")
//                .withWebEvent("share", new TestEvent())
                .configure();
        initLeakCanary();

    }

    // 1. 在 Application 中定义一个获取 RefWatcher 的静态方法
    public static RefWatcher getRefWatcher() {
        return LeakCanary.installedRefWatcher();
    }

    private void initLeakCanary() {
        // 不需要在 LeakCanary 用来做堆分析的进程中初始化 LeakCanary
        if (!LeakCanary.isInAnalyzerProcess(this)) {
            LeakCanary.install(this);
        }
    }
}
