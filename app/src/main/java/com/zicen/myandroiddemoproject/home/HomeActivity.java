package com.zicen.myandroiddemoproject.home;

import android.os.Bundle;

import com.yjnull.latte_core.activities.ProxyActivity;
import com.yjnull.latte_core.app.Latte;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import me.yokeyword.fragmentation.ISupportFragment;

/**
 * 主 Activity 首先是要进行一个 Splash 界面，然后再进入到 主页的 Fragment
 */
public class HomeActivity extends ProxyActivity {

    @Override
    public ISupportFragment setRootDelegate() {
        return new HomeFragment();
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        Latte.getConfigurator().withActivity(this);
    }
}
