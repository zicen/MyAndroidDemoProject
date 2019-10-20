package com.zicen.myandroiddemoproject.home;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.yjnull.latte_core.fragment.BaseMainFragment;
import com.zicen.myandroiddemoproject.R;
import com.zicen.myandroiddemoproject.R2;

import butterknife.BindView;
import butterknife.OnClick;

public class DiscoverFragment extends BaseMainFragment {

    @BindView(R2.id.txt_title)
    TextView txtTitle;

    @OnClick(R.id.txt_title)
    void onClick() {
        ToastUtils.showShort("ahahhaa");
    }

    @Override
    protected Object setLayout() {
        return  R.layout.fragment_discover;
    }

    @Override
    protected void initView(Bundle savedInstanceState, View rootView) {
        txtTitle.setText("aaaaa");
    }
}
