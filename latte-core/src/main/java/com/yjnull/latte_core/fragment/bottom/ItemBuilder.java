package com.yjnull.latte_core.fragment.bottom;

import java.util.LinkedHashMap;

/**
 * Created by zicen on 2018/7/10
 */
public final class ItemBuilder {
    //保证底部导航栏有序
    private final LinkedHashMap<BottomTabBean, BottomItemFragment> ITEMS = new LinkedHashMap<>();

    static ItemBuilder builder() {
        return new ItemBuilder();
    }

    public final ItemBuilder addItem(BottomTabBean bean, BottomItemFragment delegate) {
        ITEMS.put(bean, delegate);
        return this;
    }

    public final ItemBuilder addItems(LinkedHashMap<BottomTabBean, BottomItemFragment> items) {
        ITEMS.putAll(items);
        return this;
    }

    public final LinkedHashMap<BottomTabBean, BottomItemFragment> build() {
        return ITEMS;
    }
}
