package com.zicen.myandroiddemoproject.dynamicfragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;

import com.flyco.tablayout.SlidingTabLayout;

/**
 * 做项目过程中遇到的一个 SlidingTabLayout 的坑，就是当页面被异常杀死，回调到 onRestoreInstanceState 的时候会崩溃
 * 修复空指针异常的 SlidingTabLayout
 * https://github.com/H07000223/FlycoTabLayout/pull/392
 */
public class FixNpeSlidingTabLayout extends SlidingTabLayout {
    public FixNpeSlidingTabLayout(Context context) {
        super(context);
    }

    public FixNpeSlidingTabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Bundle) {
            Bundle bundle = (Bundle) state;
            int currentTab = bundle.getInt("mCurrentTab");
            int tabCount = getTabCount();
            if (currentTab >= tabCount) {
                bundle.putInt("mCurrentTab", 0);
            }
        }
        super.onRestoreInstanceState(state);
    }
}
