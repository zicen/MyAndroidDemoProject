package com.zicen.myandroiddemoproject.tick;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zicen.myandroiddemoproject.MyApplication;
import com.zicen.myandroiddemoproject.R;
import com.zicen.myandroiddemoproject.tick.TickHandler;
import com.zicen.myandroiddemoproject.utils.DateConvertUtils;

public class LeakActivity extends AppCompatActivity implements View.OnClickListener, TickHandler.OnTickListener {
    private TextView mTxtTime;
    private Button mBtnStartTimer;
    private TickHandler tickHandler;
    private long startTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leak);
        MyApplication.getRefWatcher().watch(this);

        mTxtTime = (TextView) findViewById(R.id.txt_time);
        mBtnStartTimer = (Button) findViewById(R.id.btn_start_timer);
        mBtnStartTimer.setOnClickListener(this);

        tickHandler = new TickHandler(this);
        tickHandler.setOnTickChangedListener(this);
        tickHandler.startTick(0);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start_timer:
                if (tickHandler != null) {
                    tickHandler.updateTick(startTime);
                }
                break;
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (tickHandler != null) {
            tickHandler.stopTick();
        }
    }

    @Override
    public void onTickStateChanged(boolean isLive) {
        mTxtTime.setText(isLive ? "" : " 暂无直播 ");
    }

    @Override
    public void onTickTimeChanged(long tickTime) {
        mTxtTime.setText(" 正在直播 " + DateConvertUtils.getTime(tickTime));
    }
}
