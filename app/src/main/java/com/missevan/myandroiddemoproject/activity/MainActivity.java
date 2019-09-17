package com.missevan.myandroiddemoproject.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.missevan.myandroiddemoproject.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mBtnViewpager;
    private Button mBtnSpan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mBtnViewpager = findViewById(R.id.btn_viewpager);
        mBtnSpan = findViewById(R.id.btn_span);
        mBtnViewpager.setOnClickListener(this);
        mBtnSpan.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_viewpager:
                startActivity(new Intent(this,ViewPagerDemoActivity.class));
                break;
            case R.id.btn_span:
                startActivity(new Intent(this,DraweeSpanDemoActivity.class));
                break;
        }
    }
}
