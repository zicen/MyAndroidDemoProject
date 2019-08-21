package com.missevan.myandroiddemoproject.adapter;

import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.ViewGroup;

import com.missevan.myandroiddemoproject.model.ViewPagerInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * 动态设置 Fragment 的适配器 自己处理 instantiateItem 以及 destroyItem
 */
public class NewHomePagerFragmentAdapter extends FragmentStatePagerAdapter {
    private FragmentManager mFragmentManager;
    private List<ViewPagerInfo> mTabs = new ArrayList<>();
    private List<Fragment> mFragments = new ArrayList<>();

    public NewHomePagerFragmentAdapter(FragmentManager fm, List<ViewPagerInfo> list) {
        super(fm);
        this.mFragmentManager = fm;
        if (list == null) return;
        this.mTabs.addAll(list);
        for (ViewPagerInfo dynamicFragmentEntity : mTabs) {
            mFragments.add(dynamicFragmentEntity.getFragment());
        }
    }

    public void updateData(List<ViewPagerInfo> list) {
        if (list == null) return;
        this.mFragments.clear();
        this.mTabs.clear();
        this.mTabs.addAll(list);
        for (ViewPagerInfo dynamicFragmentEntity : mTabs) {
            mFragments.add(dynamicFragmentEntity.getFragment());
        }
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTabs.get(position).getTitle();
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public Parcelable saveState() {
        return null;
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        if (!((Fragment) object).isAdded() || !mFragments.contains(object)) {
            return PagerAdapter.POSITION_NONE;
        }
        return mFragments.indexOf(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        Fragment instantiateItem = ((Fragment) super.instantiateItem(container, position));
        Fragment item = mFragments.get(position);
        if (instantiateItem == item) {
            return instantiateItem;
        } else {
            mFragmentManager.beginTransaction().add(container.getId(), item).commitNowAllowingStateLoss();
            return item;
        }
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        Fragment fragment = (Fragment) object;
        if (mFragments.contains(fragment)) {
            super.destroyItem(container, position, fragment);
            return;
        }
        mFragmentManager.beginTransaction().remove(fragment).commitNowAllowingStateLoss();

    }
}