package com.example.studyplan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import Util.MyBroadCast;

public class BReceiveTest extends AppCompatActivity {
    MyBroadCast broadCast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breceivetest);
        //这是动态注册，程序启动之后可以监听到广播
//        broadCast = new MyBroadCast();
//        IntentFilter filter = new IntentFilter();
//        filter.addAction(broadCast.bootStart);
//        registerReceiver(broadCast,filter);

        broadCast = new MyBroadCast();
        IntentFilter filter = new IntentFilter("testBroadCast");
        this.registerReceiver(broadCast,filter);

        //发送自定义广播
        TextView tv = this.findViewById(R.id.sendMyBr);
        tv.setClickable(true);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("testBroadCast");
                intent.putExtra("message","你妈喊你回家吃饭了");
                sendBroadcast(intent);
            }
        });

        MyBroadCast local = new MyBroadCast();
        IntentFilter filter1 = new IntentFilter("loginOther");

        LocalBroadcastManager manger = LocalBroadcastManager.getInstance(this);
        manger.registerReceiver(local,filter1);

        //发送自定义广播
        TextView tv1 = this.findViewById(R.id.sendDefineBr);
        tv1.setClickable(true);
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("loginOther");
                intent.setPackage(getPackageName());
                intent.putExtra("message","你妈喊你回家吃饭了");
                sendBroadcast(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadCast);
    }
}
