package com.missevan.myandroiddemoproject.model;

import android.support.v4.app.Fragment;

public class ViewPagerInfo {
    private Fragment fragment;
    private String title;
    private int index;

    public Fragment getFragment() {
        return fragment;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
