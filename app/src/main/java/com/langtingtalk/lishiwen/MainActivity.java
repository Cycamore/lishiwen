package com.langtingtalk.lishiwen;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.langtingtalk.lishiwen.fragment.GridFragment;
import com.langtingtalk.lishiwen.fragment.SecondFragment;
import com.langtingtalk.lishiwen.fragment.UserFragment;
import com.langtingtalk.lishiwen.fragment.WeChatFragment;
import com.langtingtalk.lishiwen.ui.BaseActivity;
import com.langtingtalk.lishiwen.ui.SettingsActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private List<String> mTabTitle;
    private List<Fragment> mFragment;
    private FloatingActionButton mFab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //去掉actionbar阴影
        getSupportActionBar().setElevation(0);
        initData();
        initView();
    }

    private void initData() {
        //init title
        mTabTitle = new ArrayList<>();
        mTabTitle.add(getString(R.string.tab_1));
        mTabTitle.add(getString(R.string.tab_2));
        mTabTitle.add(getString(R.string.tab_3));
        mTabTitle.add(getString(R.string.tab_4));
        //init fragment
        mFragment = new ArrayList<>();
        mFragment.add(new SecondFragment());
        mFragment.add(new WeChatFragment());
        mFragment.add(new GridFragment());
        mFragment.add(new UserFragment());


    }

    private void initView() {
        mFab = (FloatingActionButton) findViewById(R.id.fab);
        mFab.setOnClickListener(this);
        mTabLayout = (TabLayout) findViewById(R.id.mTabLayout);
        mViewPager = (ViewPager) findViewById(R.id.mViewPager);
        //预加载
        mViewPager.setOffscreenPageLimit(mFragment.size());
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {

            @Override
            public Fragment getItem(int position) {
                return mFragment.get(position);
            }

            @Override
            public int getCount() {
                return mFragment.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return mTabTitle.get(position);
            }
        });
        //绑定
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                startActivity(new Intent(this, SettingsActivity.class));
                break;
        }
    }
}
