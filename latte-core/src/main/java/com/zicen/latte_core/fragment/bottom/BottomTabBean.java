package com.zicen.latte_core.fragment.bottom;

/**
 * Created by zicen
 */
public final class BottomTabBean {
    // 图片资源（要用一张透明的图片，好上色）
    private final Integer ICON;
    private final CharSequence TITLE;

    public BottomTabBean(Integer icon, CharSequence title) {
        this.ICON = icon;
        this.TITLE = title;
    }

    public Integer getIcon() {
        return ICON;
    }

    public CharSequence getTitle() {
        return TITLE;
    }
}
