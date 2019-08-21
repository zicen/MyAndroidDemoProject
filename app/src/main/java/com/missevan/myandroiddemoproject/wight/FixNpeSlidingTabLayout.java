package com.missevan.myandroiddemoproject.wight;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;

import com.flyco.tablayout.SlidingTabLayout;

/**
 * 做项目过程中遇到的一个 SlidingTabLayout 的坑，就是当页面被异常杀死，回调到 onRestoreInstanceState 的时候会崩溃
 * 修复空指针异常的 SlidingTabLayout
 * https://github.com/H07000223/FlycoTabLayout/pull/392
 *
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
            int currentTab;
            int tabCount = getTabCount();
            if (tabCount <= 2) {//没有直播，定位到 推荐页
                currentTab = 0;
            } else if (tabCount == 3) {//有直播，定位到 推荐页
                currentTab = 1;
            } else {//其他情况(应该就是原本有直播 tab 然后定位到的是分类页，所以 index = 2,然后 restore 的时候没有直播 tab 了，所以出现了 NPE ) 定位到最后一页
                currentTab = tabCount - 1;
            }
            bundle.putInt("mCurrentTab", currentTab);
        }
        super.onRestoreInstanceState(state);
    }
}
