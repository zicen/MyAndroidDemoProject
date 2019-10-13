package com.zicen.myandroiddemoproject.leak;

import android.app.Activity;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.zicen.myandroiddemoproject.MyApplication;
import com.zicen.myandroiddemoproject.R;

import java.util.ArrayList;
import java.util.List;

public class MemoryLeakActivity extends AppCompatActivity {
    public static List<Activity> activities = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_leak);
        MyApplication.getRefWatcher().watch(this);
        activities.add(this);
    }
}
