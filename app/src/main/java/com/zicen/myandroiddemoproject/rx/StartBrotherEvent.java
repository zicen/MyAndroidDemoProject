package com.zicen.myandroiddemoproject.rx;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by Nick on 20/09/2017.
 */

public class StartBrotherEvent {
    public SupportFragment targetFragment;
    public Class<?> targetActivity;

    public SupportFragment getTargetFragment() {
        return targetFragment;
    }

    public void setTargetFragment(SupportFragment targetFragment) {
        this.targetFragment = targetFragment;
    }

    public StartBrotherEvent(SupportFragment targetFragment) {
        this.targetFragment = targetFragment;
    }

    public StartBrotherEvent(Class<?> targetActivityClass) {
        this.targetActivity = targetActivityClass;
    }

}
