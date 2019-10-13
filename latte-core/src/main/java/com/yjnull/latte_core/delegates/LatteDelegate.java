package com.yjnull.latte_core.delegates;

/**
 * Created by zicen on 2018/7/3
 */
public abstract class LatteDelegate extends BaseDelegate {

    @SuppressWarnings("unchecked")
    public <T extends LatteDelegate> T getParentDelegate() {
        return (T) getParentFragment();
    }

}