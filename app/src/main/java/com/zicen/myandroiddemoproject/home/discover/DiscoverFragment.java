package com.zicen.myandroiddemoproject.home.discover;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.yjnull.latte_core.fragment.bottom.BottomItemFragment;
import com.zicen.myandroiddemoproject.R;
import com.zicen.myandroiddemoproject.R2;

import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.OnClick;

public class DiscoverFragment extends BottomItemFragment {

    @BindView(R2.id.txt_title)
    TextView txtTitle;

    @Override
    public Object setLayout() {
        return R.layout.fragment_discover;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        txtTitle.setText("aaaaa");
    }

    @OnClick(R.id.txt_title)
    void onClick() {
        ToastUtils.showShort("ahahhaa");
    }
}
