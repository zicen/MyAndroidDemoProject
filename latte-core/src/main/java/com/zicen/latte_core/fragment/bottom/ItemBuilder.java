package com.zicen.latte_core.fragment.bottom;

import com.zicen.latte_core.fragment.BaseMainFragment;

import java.util.LinkedHashMap;

/**
 * Created by zicen
 */
public final class ItemBuilder {
    //保证底部导航栏有序
    private final LinkedHashMap<BottomTabBean, BaseMainFragment> ITEMS = new LinkedHashMap<>();

    static ItemBuilder builder() {
        return new ItemBuilder();
    }

    public final ItemBuilder addItem(BottomTabBean bean, BaseMainFragment delegate) {
        ITEMS.put(bean, delegate);
        return this;
    }

    public final ItemBuilder addItems(LinkedHashMap<BottomTabBean, BaseMainFragment> items) {
        ITEMS.putAll(items);
        return this;
    }

    public final LinkedHashMap<BottomTabBean, BaseMainFragment> build() {
        return ITEMS;
    }
}
