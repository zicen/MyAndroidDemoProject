package com.missevan.myandroiddemoproject.model;

import android.support.v4.app.Fragment;

public class ViewPagerInfo {
    private Fragment fragment;
    private String title;
;

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

}
