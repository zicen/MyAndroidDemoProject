package com.zicen.myandroiddemoproject.leak;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import com.zicen.myandroiddemoproject.utils.DateConvertUtils;

import java.lang.ref.WeakReference;


public class TickHandler extends Handler {
    private long totalTickTime = 0;
    private boolean isTicking = false;
    private TextView stateText;
    private WeakReference<Context> wContext;

    public TickHandler(Context context, TextView stateText) {
        this.wContext = new WeakReference<>(context);
        this.stateText = stateText;
    }

    /**
     * Start the timer
     *
     * @param startTime 开始时已经直播的时长
     */
    public void startTick(long startTime) {
        if (isTicking) {//如果正在计时，则直接返回
            return;
        }
        totalTickTime = startTime;
        sendEmptyMessageDelayed(0, 1000);
        updateLiveState(true);
        isTicking = true;
    }

    /**
     * 更新已经直播的时长
     */
    public void updateTick(long startTime) {
        removeCallbacksAndMessages(null);
        totalTickTime = startTime;
        sendEmptyMessageDelayed(0, 1000);
        updateLiveState(true);
        isTicking = true;
    }

    /**
     * Stop the timer
     */
    public void stopTick() {
        removeCallbacksAndMessages(null);
        updateLiveState(false);
        if (!isTicking) {
            return;
        }
        isTicking = false;
    }

    /**
     * Update room live state
     *
     * @param isLive true: on-line false: off-line
     */
    private void updateLiveState(boolean isLive) {
        if (stateText == null || wContext.get() == null) {
            return;
        }
        stateText.setText(isLive ? "" : " 暂无直播 ");
    }

    @Override
    public void handleMessage(Message msg) {
        if (stateText == null) return;
        totalTickTime += 1000;
        if (wContext.get() != null) {
            stateText.setText(" 正在直播 " + DateConvertUtils.getTime(totalTickTime));
        }
        //每秒更新一次
        if (isTicking) {
            sendEmptyMessageDelayed(0, 1000);
        }
    }

    /**
     * 获取当前播放的时长
     *
     * @return
     */
    public long getTotalTickTime() {
        return totalTickTime;
    }

}
