package com.zicen.myandroiddemoproject.tick;

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
    private WeakReference<Context> wContext;

    public TickHandler(Context context) {
        this.wContext = new WeakReference<>(context);
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
        if (listener != null) {
            listener.onTickStateChanged(isLive);
        }
    }

    @Override
    public void handleMessage(Message msg) {
        totalTickTime += 1000;
        if (wContext.get() != null && listener != null) {
            listener.onTickTimeChanged(totalTickTime);
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

    private OnTickListener listener;

    public void setOnTickChangedListener(OnTickListener listener) {
        this.listener = listener;
    }

    public interface OnTickListener {
        void onTickStateChanged(boolean state);

        void onTickTimeChanged(long tickTime);
    }
}
