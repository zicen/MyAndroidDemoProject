package com.zicen.myandroiddemoproject.activity;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.zicen.myandroiddemoproject.R;
import com.zicen.myandroiddemoproject.adapter.NewHomePagerFragmentAdapter;
import com.zicen.myandroiddemoproject.fragment.SimpleCardFragment;
import com.zicen.myandroiddemoproject.model.ViewPagerInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * 做项目过程中遇到的一个 SlidingTabLayout 的坑，就是当页面被异常杀死，回调到 onRestoreInstanceState 的时候会崩溃
 */
public class ViewPagerDemoActivity extends AppCompatActivity implements OnTabSelectListener, View.OnClickListener {
    private Context mContext = this;
    private SlidingTabLayout mTlBar;
    private ViewPager mVpContainer;
    private Button mBtnAdd;
    private Button mBtnRemove;
    private Button mBtnRecreate;
    private NewHomePagerFragmentAdapter mAdapter;
    private List<ViewPagerInfo> mShowDatas = new ArrayList<>();
    private ViewPagerInfo recommendPagerInfo;
    private ViewPagerInfo catelogPagerInfo;
    private ViewPagerInfo livePagerInfo;

    private SimpleCardFragment recommendFragment = SimpleCardFragment.getInstance("推荐");
    private SimpleCardFragment catelogFragment = SimpleCardFragment.getInstance("分类");
    private SimpleCardFragment liveFragment = SimpleCardFragment.getInstance("直播");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager_demo);
        // initView
        mTlBar = findViewById(R.id.tl_bar);
        mVpContainer = findViewById(R.id.vp_container);
        mBtnAdd = findViewById(R.id.btn_add);
        mBtnRemove = findViewById(R.id.btn_remove);
        mBtnRecreate = findViewById(R.id.btn_recreate);

        mBtnAdd.setOnClickListener(this);
        mBtnRemove.setOnClickListener(this);
        mBtnRecreate.setOnClickListener(this);

        // initData
        recommendPagerInfo = new ViewPagerInfo();
        recommendPagerInfo.setFragment(recommendFragment);
        recommendPagerInfo.setTitle("推荐");

        catelogPagerInfo = new ViewPagerInfo();
        catelogPagerInfo.setFragment(catelogFragment);
        catelogPagerInfo.setTitle("分类");

        livePagerInfo = new ViewPagerInfo();
        livePagerInfo.setFragment(liveFragment);
        livePagerInfo.setTitle("直播");

        mShowDatas.add(recommendPagerInfo);
        mShowDatas.add(catelogPagerInfo);

        // reload
        if (savedInstanceState != null) {
            boolean isShowLive = savedInstanceState.getBoolean("isShowLive");
            if (isShowLive) mShowDatas.add(0,livePagerInfo);
        }

        mAdapter = new NewHomePagerFragmentAdapter(getSupportFragmentManager(), mShowDatas);
        mVpContainer.setAdapter(mAdapter);
        mTlBar.setViewPager(mVpContainer);
        mTlBar.setOnTabSelectListener(this);

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("isShowLive", mShowDatas.contains(livePagerInfo));
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

    }

    @Override
    public void onTabSelect(int position) {
        Toast.makeText(mContext, "onTabSelect&position--->" + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTabReselect(int position) {
        Toast.makeText(mContext, "onTabReselect&position--->" + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add:
                if (!mShowDatas.contains(livePagerInfo)) {
                    mShowDatas.add(0, livePagerInfo);
                    mAdapter.updateData(mShowDatas);
                    mVpContainer.setAdapter(mAdapter);
                    mTlBar.setViewPager(mVpContainer);
                }
                break;
            case R.id.btn_remove:
                if (mShowDatas.contains(livePagerInfo)) {
                    mShowDatas.remove(livePagerInfo);
                    mAdapter.updateData(mShowDatas);
                    mVpContainer.setAdapter(mAdapter);
                    mTlBar.setViewPager(mVpContainer);
                }
                break;

            case R.id.btn_recreate:
                ViewPagerDemoActivity.this.recreate();
                mVpContainer.setAdapter(mAdapter);
                mTlBar.setViewPager(mVpContainer);
                break;
        }
    }

}
