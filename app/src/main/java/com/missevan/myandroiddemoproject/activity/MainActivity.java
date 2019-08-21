package com.missevan.myandroiddemoproject.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.missevan.myandroiddemoproject.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mBtnViewpager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mBtnViewpager = findViewById(R.id.btn_viewpager);
        mBtnViewpager.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_viewpager:
                startActivity(new Intent(this,ViewPagerDemoActivity.class));
                break;
        }
    }
}
