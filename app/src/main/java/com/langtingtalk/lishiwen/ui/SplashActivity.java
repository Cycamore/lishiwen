package com.langtingtalk.lishiwen.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.langtingtalk.lishiwen.R;
import com.langtingtalk.lishiwen.utils.ShareUtils;
import com.langtingtalk.lishiwen.utils.StaticClass;

public class SplashActivity extends AppCompatActivity {
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case StaticClass.HANDLER_MSG:
                    if (isFirstRun()) {
                        Log.d("TAG", "handleMessage: ");
                        startActivity(new Intent(SplashActivity.this, GuideActivity.class));
                    } else {
                        startActivity(new Intent(SplashActivity.this, GuideActivity.class));
                    }


                    finish();
                    break;


            }
            super.handleMessage(msg);
        }
    };

    private boolean isFirstRun() {
        boolean isFirstRun = ShareUtils.getBoolean(this, StaticClass.TAG_ISFIRSTRUN, true);
        if (isFirstRun) {
            ShareUtils.putBoolean(this, StaticClass.TAG_ISFIRSTRUN, false);
            return true;

        } else {
            return false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);

        mHandler.sendEmptyMessageDelayed(StaticClass.HANDLER_MSG, 2000);

//// TODO: 2017/10/17
    }

}
