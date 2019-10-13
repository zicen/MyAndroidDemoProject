package com.zicen.myandroiddemoproject.home;

import android.graphics.Color;

import com.yjnull.latte_core.fragment.bottom.BaseBottomDelegate;
import com.yjnull.latte_core.fragment.bottom.BottomItemFragment;
import com.yjnull.latte_core.fragment.bottom.BottomTabBean;
import com.yjnull.latte_core.fragment.bottom.ItemBuilder;
import com.zicen.myandroiddemoproject.home.discover.DiscoverFragment;
import com.zicen.myandroiddemoproject.home.index.IndexFragment;

import java.util.LinkedHashMap;


/**
 * Created by Yangya on 2018/7/10
 */
public class HomeFragment extends BaseBottomDelegate {

    @Override
    public LinkedHashMap<BottomTabBean, BottomItemFragment> setItems(ItemBuilder builder) {
        final LinkedHashMap<BottomTabBean, BottomItemFragment> items = new LinkedHashMap<>();
        items.put(new BottomTabBean("{fa-home}", "主页"), new IndexFragment());
        items.put(new BottomTabBean("{fa-sort}", "分类"), new DiscoverFragment());
        items.put(new BottomTabBean("{fa-compass}", "发现"), new IndexFragment());
        items.put(new BottomTabBean("{fa-shopping-cart}", "购物车"), new IndexFragment());
        items.put(new BottomTabBean("{fa-user}", "我的"), new IndexFragment());
        return builder.addItems(items).build();
    }

    @Override
    public int setIndexDelegate() {
        return 0;
    }

    @Override
    public int setClickedColor() {
        return Color.parseColor("#ffff8800");
    }
}
