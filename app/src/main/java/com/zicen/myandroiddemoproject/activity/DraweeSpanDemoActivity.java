package com.zicen.myandroiddemoproject.activity;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.ScrollingMovementMethod;
import android.text.style.ImageSpan;
import android.view.View;
import android.widget.TextView;

import com.zicen.myandroiddemoproject.R;
import com.zicen.myandroiddemoproject.wight.draweetext.DraweeSpan;
import com.zicen.myandroiddemoproject.wight.draweetext.DraweeTextView;

public class DraweeSpanDemoActivity extends AppCompatActivity {
    private TextView mTvAnnouncement;
    private DraweeTextView mTvAnnouncement2;
    private String notice = "23132124124214221asdadaw12313213212的卡我大号我的骄傲我的卡";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawee_span_demo);
        mTvAnnouncement = findViewById(R.id.tv_announcement);
        mTvAnnouncement2 = findViewById(R.id.tv_announcement2);

        setNotice();
        setNotive2();
    }

    private void setNotive2() {
        SpannableStringBuilder builder = new SpannableStringBuilder();
        Drawable drawable = getResources().getDrawable(R.mipmap.icon_live_notice);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        DraweeSpan span = new DraweeSpan.Builder("http://static.yo9.com/web/emotions/tv_cheers.png", true).build();
        span.setImage(drawable);
        builder.append(notice);
        builder.setSpan(span, 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        mTvAnnouncement2.setText(builder);
    }

    private void setNotice() {
        SpannableString hint = new SpannableString("  " + notice);
        Drawable drawable = getResources().getDrawable(R.mipmap.icon_live_notice);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        ImageSpan imageSpan = new ImageSpan(drawable, ImageSpan.ALIGN_BASELINE);
        hint.setSpan(imageSpan, 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        mTvAnnouncement.setText(hint);
        mTvAnnouncement.setMovementMethod(ScrollingMovementMethod.getInstance());
    }
}
