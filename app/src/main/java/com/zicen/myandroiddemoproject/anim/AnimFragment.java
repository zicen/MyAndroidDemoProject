package com.zicen.myandroiddemoproject.anim;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.zicen.latte_core.fragment.BaseBackFragment;
import com.zicen.myandroiddemoproject.R;

import butterknife.BindView;
import butterknife.OnClick;

public class AnimFragment extends BaseBackFragment {
    @BindView(R.id.view)
    View animView;
    @BindView(R.id.btn_stop_anim)
    Button btnStopAnim;
    @BindView(R.id.btn_start_anim)
    Button btnStartAnim;
    private AnimatorSet animSet;

    @Override
    protected Object setLayout() {
        return R.layout.anim_fragment;
    }

    @Override
    protected void initView(Bundle savedInstanceState, View rootView) {


    }

    @OnClick({R.id.btn_stop_anim, R.id.btn_start_anim})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_stop_anim:
                stopAnim();
                break;
            case R.id.btn_start_anim:
                startAnim();
                break;
        }
    }

    private void stopAnim() {
        if (animSet != null) {
            animSet.cancel();
        }
    }

    private void startAnim() {
        stopAnim();
        ObjectAnimator anim1_alpha = ObjectAnimator.ofFloat(animView, "alpha", 0f, 1f);
        ObjectAnimator anim1_alpha2 = ObjectAnimator.ofFloat(animView, "alpha", 1f, 0f);
        // 位移动画：水平左移然后复位
        ObjectAnimator anim_translationX = ObjectAnimator.ofFloat(animView, "translationX", -ScreenUtils.getAppScreenWidth());
        ObjectAnimator anim_translationX2 = ObjectAnimator.ofFloat(animView, "translationX", -ScreenUtils.getAppScreenWidth() * 1.5f);
        anim1_alpha.setDuration(500);
        anim1_alpha2.setDuration(500);
        anim_translationX.setDuration(500);
        anim_translationX2.setDuration(500);
        animSet = new AnimatorSet();
        // 动画执行的监听回调事件
        animSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                LogUtils.e("onAnimationEnd:");
                // 动画结束 设置控件到初始位置
                animView.setX(ScreenUtils.getAppScreenWidth());
                animView.requestLayout();
            }
        });

        animSet.play(anim1_alpha).with(anim_translationX);
        animSet.play(anim1_alpha2).with(anim_translationX2).after(1000);
        // 正式启动动画集
        animSet.start();
    }
}
