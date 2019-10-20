package com.yjnull.latte_core.fragment.bottom;

import android.os.Bundle;
import android.view.View;

import com.yjnull.latte_core.R;
import com.yjnull.latte_core.R2;
import com.yjnull.latte_core.fragment.BaseMainFragment;
import com.yjnull.latte_core.ui.loader.bottom.view.BottomBar;
import com.yjnull.latte_core.ui.loader.bottom.view.BottomBarTab;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import androidx.annotation.Nullable;
import butterknife.BindView;
import me.yokeyword.fragmentation.ISupportFragment;

/**
 * Created by zicen
 */
public abstract class BaseBottomDelegate extends BaseMainFragment {

    private final ArrayList<BottomTabBean> TAB_BEANS = new ArrayList<>();
    private final ArrayList<BaseMainFragment> ITEM_DELEGATES = new ArrayList<>();
    private final LinkedHashMap<BottomTabBean, BaseMainFragment> ITEMS = new LinkedHashMap<>();

    private int mCurrentDelegate = 0; //当前页面
    private int mIndexDelegate = 0;   //进入首页时，需要展示哪个页面

    @BindView(R2.id.bottomBar)
    BottomBar mBottomBar = null;

    public abstract LinkedHashMap<BottomTabBean, BaseMainFragment> setItems(ItemBuilder builder);

    @Override
    public Object setLayout() {
        return R.layout.delegate_bottom;
    }

    public abstract int setIndexDelegate();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIndexDelegate = setIndexDelegate();

        final ItemBuilder builder = ItemBuilder.builder();
        final LinkedHashMap<BottomTabBean, BaseMainFragment> items = setItems(builder);
        ITEMS.putAll(items);
        for (Map.Entry<BottomTabBean, BaseMainFragment> item : ITEMS.entrySet()) {
            final BottomTabBean key = item.getKey();
            final BaseMainFragment value = item.getValue();
            TAB_BEANS.add(key);
            ITEM_DELEGATES.add(value);
        }
    }

    public void initView(@Nullable Bundle savedInstanceState, View rootView) {

        for (BottomTabBean bottomTabBean : TAB_BEANS) {
            mBottomBar.addItem(new BottomBarTab(_mActivity, bottomTabBean.getIcon(), bottomTabBean.getTitle()));
        }
        mBottomBar.setOnTabSelectedListener(new BottomBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position, int prePosition) {
                //Fragment显示隐藏，一定要注意先后顺序
                getSupportDelegate().showHideFragment(ITEM_DELEGATES.get(position), ITEM_DELEGATES.get(prePosition));
                mCurrentDelegate = position;
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });

        //加载多个同级根Fragment,类似Wechat, QQ主页的场景
        final ISupportFragment[] delegateArray = ITEM_DELEGATES.toArray(new ISupportFragment[ITEMS.size()]);
        getSupportDelegate().loadMultipleRootFragment(R.id.fl_tab_container, mIndexDelegate, delegateArray);
    }


}
