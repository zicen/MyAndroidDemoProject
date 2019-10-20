package com.zicen.myandroiddemoproject.home;

import android.os.Bundle;

import com.jaeger.library.StatusBarUtil;
import com.zicen.latte_core.activities.ProxyActivity;
import com.zicen.latte_core.app.Latte;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import me.yokeyword.fragmentation.ISupportFragment;


public class MainActivity extends ProxyActivity {

    @Override
    public ISupportFragment setRootDelegate() {
        return new MainFragment();
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
