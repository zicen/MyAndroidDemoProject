package com.zicen.latte_core.util.callback;

import androidx.annotation.Nullable;

/**
 * Created by zicen on 2018/7/16
 */
public interface IGlobalCallback<T> {

    void executeCallback(@Nullable T args);
}
