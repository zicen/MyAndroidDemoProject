package com.zicen.myandroiddemoproject.home;

import android.content.Intent;
import android.os.Bundle;

import com.zicen.latte_core.fragment.BaseMainFragment;
import com.zicen.latte_core.fragment.bottom.BaseBottomDelegate;
import com.zicen.latte_core.fragment.bottom.BottomTabBean;
import com.zicen.latte_core.fragment.bottom.ItemBuilder;
import com.zicen.myandroiddemoproject.R;
import com.zicen.myandroiddemoproject.rx.AppConstants;
import com.zicen.myandroiddemoproject.rx.RxBus;
import com.zicen.myandroiddemoproject.rx.RxManager;
import com.zicen.myandroiddemoproject.rx.StartBrotherEvent;

import java.util.LinkedHashMap;

import androidx.annotation.Nullable;
import io.reactivex.functions.Consumer;


/**
 * Created by zicen
 */
public class MainFragment extends BaseBottomDelegate {
    private RxManager mRxManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRxManager = new RxManager();
        mRxManager.on(AppConstants.START_FRAGMENT, (Consumer<StartBrotherEvent>) startBrotherEvent -> {
            if (startBrotherEvent != null) {
                start(startBrotherEvent.targetFragment);
            }
        });
        mRxManager.on(AppConstants.START_ACTIVITY, (Consumer<StartBrotherEvent>) startBrotherEvent -> {
            if (startBrotherEvent != null && startBrotherEvent.targetActivity != null) {
                Intent intent = new Intent(_mActivity, startBrotherEvent.targetActivity);
                startActivity(intent);
            }
        });
        mRxManager.on(AppConstants.START_FRAGMENT_SINGLETOP, (Consumer<StartBrotherEvent>) startBrotherEvent -> {
            if (startBrotherEvent != null) {
                start(startBrotherEvent.targetFragment, SINGLETOP);
            }
        });
        mRxManager.on(AppConstants.START_FRAGMENT_SINGLETASK, (Consumer<StartBrotherEvent>) startBrotherEvent -> {
            if (startBrotherEvent != null) {
                start(startBrotherEvent.targetFragment, SINGLETASK);
            }
        });
    }

    @Override
    public LinkedHashMap<BottomTabBean, BaseMainFragment> setItems(ItemBuilder builder) {
        final LinkedHashMap<BottomTabBean, BaseMainFragment> items = new LinkedHashMap<>();
        items.put(new BottomTabBean(R.mipmap.ic_discover_white_24dp, "发现"), new DiscoverFragment());
        items.put(new BottomTabBean(R.mipmap.ic_message_white_24dp, "消息"), new IndexFragment());
        items.put(new BottomTabBean(R.mipmap.ic_account_circle_white_24dp, "账户"), new WebFragment());
        return items;
    }

    @Override
    public int setIndexDelegate() {
        return 0;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        RxBus.getInstance().unregister(this);
        try {
            if (mRxManager != null)
                mRxManager.clear();
        } catch (Exception ignored) {
        }
    }
}
