package com.zicen.myandroiddemoproject.dynamicfragment;

import androidx.fragment.app.Fragment;

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
