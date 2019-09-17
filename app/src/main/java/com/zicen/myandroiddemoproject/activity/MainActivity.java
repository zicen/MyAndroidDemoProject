package com.zicen.myandroiddemoproject.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.zicen.myandroiddemoproject.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mBtnViewpager;
    private Button mBtnSpan;
    private Button mBtnLeak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mBtnViewpager = findViewById(R.id.btn_viewpager);
        mBtnSpan = findViewById(R.id.btn_span);
        mBtnLeak = findViewById(R.id.btn_leak);
        mBtnViewpager.setOnClickListener(this);
        mBtnSpan.setOnClickListener(this);
        mBtnLeak.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_leak:
                startActivity(new Intent(this, LeakActivity.class));
                break;
            case R.id.btn_viewpager:
                startActivity(new Intent(this, ViewPagerDemoActivity.class));
                break;
            case R.id.btn_span:
                startActivity(new Intent(this, DraweeSpanDemoActivity.class));
                break;
        }
    }
}
