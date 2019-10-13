package com.zicen.myandroiddemoproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.zicen.myandroiddemoproject.R;
import com.zicen.myandroiddemoproject.dynamicfragment.ViewPagerDemoActivity;
import com.zicen.myandroiddemoproject.home.HomeActivity;
import com.zicen.myandroiddemoproject.leak.MemoryLeakActivity;
import com.zicen.myandroiddemoproject.tick.LeakActivity;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    @BindView(R.id.btn_viewpager)
    Button btnViewpager;
    @BindView(R.id.btn_home)
    Button btnHome;
    @BindView(R.id.btn_leak)
    Button btnLeak;
    @BindView(R.id.btn_leak2)
    Button btnLeak2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }


    @OnClick({R.id.btn_viewpager, R.id.btn_home, R.id.btn_leak, R.id.btn_leak2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_viewpager:
                startActivity(new Intent(this, ViewPagerDemoActivity.class));
                break;
            case R.id.btn_home:
                startActivity(new Intent(this, HomeActivity.class));
                break;
            case R.id.btn_leak:
                startActivity(new Intent(this, LeakActivity.class));
                break;
            case R.id.btn_leak2:
                startActivity(new Intent(this, MemoryLeakActivity.class));
                break;
        }
    }
}
