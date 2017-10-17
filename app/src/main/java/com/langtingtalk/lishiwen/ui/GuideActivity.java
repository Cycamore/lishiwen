package com.langtingtalk.lishiwen.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.langtingtalk.lishiwen.MainActivity;
import com.langtingtalk.lishiwen.R;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends AppCompatActivity implements View.OnClickListener {
    ViewPager mViewPager;
    List<View> mPagerList;
    ImageView mPoint1, mPoint2, mPoint3;
    View mView1,mView2,mView3;
    TextView mSkip;
    Button mButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("xxxxxxxxxxxxx", "xxxxxxxxxxxxxxx");
        setContentView(R.layout.activity_guide);
        initData();

        initView();


    }

    private void initData() {
        mView1=View.inflate(this, R.layout.activity_guide_pager1, null);
        mView2=View.inflate(this, R.layout.activity_guide_pager2, null);
        mView3=View.inflate(this, R.layout.activity_guide_pager3, null);
        mView3.findViewById(R.id.btn_enter).setOnClickListener(this);

        mPagerList = new ArrayList<>();
        mPagerList.add(mView1);
        mPagerList.add(mView2);
        mPagerList.add(mView3);


    }

    private void initView() {

        mButton = (Button) findViewById(R.id.btn_enter);
//        mButton.setOnClickListener(this);

        mSkip = (TextView) findViewById(R.id.tv_skip);
        mSkip.setOnClickListener(this);


        mPoint1 = (ImageView) findViewById(R.id.point1);
        mPoint2 = (ImageView) findViewById(R.id.point2);
        mPoint3 = (ImageView) findViewById(R.id.point3);
        setPointImg(true, false, false);

        mViewPager = (ViewPager) findViewById(R.id.guide_viewpager);
        mViewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return mPagerList.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                // return super.instantiateItem(container, position);
                ((ViewPager) container).addView(mPagerList.get(position));
                return mPagerList.get(position);

            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                //super.destroyItem(container, position, object);
                ((ViewPager) container).removeView(mPagerList.get(position));

            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        setPointImg(true, false, false);
                        mSkip.setVisibility(View.VISIBLE);
                        break;
                    case 1:
                        setPointImg(false, true, false);
                        mSkip.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        setPointImg(false, false, true);
                        mSkip.setVisibility(View.GONE);
                        break;
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

    private void setPointImg(boolean point1, boolean point2, boolean point3) {
        if (point1) {
            mPoint1.setBackgroundResource(R.drawable.point_on);

        } else {
            mPoint1.setBackgroundResource(R.drawable.point_off);

        }

        if (point2) {
            mPoint2.setBackgroundResource(R.drawable.point_on);

        } else {
            mPoint2.setBackgroundResource(R.drawable.point_off);

        }
        if (point3) {
            mPoint3.setBackgroundResource(R.drawable.point_on);

        } else {
            mPoint3.setBackgroundResource(R.drawable.point_off);

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_skip:
            case R.id.btn_enter:
                startActivity(new Intent(this, MainActivity.class));
                finish();
                break;
        }
    }
}
