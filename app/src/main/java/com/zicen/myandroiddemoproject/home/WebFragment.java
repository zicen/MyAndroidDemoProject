package com.zicen.myandroiddemoproject.home;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.zicen.latte_core.fragment.BaseMainFragment;
import com.zicen.myandroiddemoproject.R;
import com.zicen.myandroiddemoproject.anim.AnimFragment;
import com.zicen.myandroiddemoproject.rx.AppConstants;
import com.zicen.myandroiddemoproject.rx.RxBus;
import com.zicen.myandroiddemoproject.rx.StartBrotherEvent;

import butterknife.BindView;
import butterknife.OnClick;

public class WebFragment extends BaseMainFragment {

    @BindView(R.id.btn_anim)
    Button btnAnim;

    @Override
    protected Object setLayout() {
        return R.layout.web_fragment;
    }

    @Override
    protected void initView(Bundle savedInstanceState, View rootView) {

    }

    @OnClick(R.id.btn_anim)
    public void onClick() {
        RxBus.getInstance().post(AppConstants.START_FRAGMENT, new StartBrotherEvent(new AnimFragment()));
    }
}
