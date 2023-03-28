package com.example.studyplan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.telecom.Call;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HandlerTest extends BaseActivity {
    ImageView icon;
    Myhandler myhandler;
    static final String UPPER_NUM = "upper";
    CalLoop loop;

/*
下面的代码也能实现效果，但由于与activity产生调用，类似iOS的循环引用，导致内存泄漏，所以单独在activity内声明一个静态内部类作为handler：
* 在Android中，使用Handler类时会涉及到Handler对象的生命周期和内存泄漏的问题。由于Handler会隐式地持有一个外部类的引用，如果外部类被销毁，而Handler对象仍然存在，则可能导致内存泄漏。

为了避免这种情况，通常需要在Handler的外部类中实现一个静态内部类，并将Handler对象作为该静态内部类的成员变量来使用。同时，需要在Handler的构造函数中传入该静态内部类的实例，以保证Handler对象与外部类无关。

然而，在某些情况下，由于特定的需求或限制，可能无法完全避免Handler对象的内存泄漏。在这种情况下，可以使用Android提供的@SuppressLint注解来抑制该警告，使代码可以正常编译和运行。具体来说，@SuppressLint("HandlerLeak")是用来告诉编译器忽略在Handler中存在内存泄漏的警告。
* */
//    int start = 0;
//    int[] images = new int[]{
//            R.mipmap.anim_1,R.mipmap.anim_2,R.mipmap.anim_3,R.mipmap.anim_4,
//            R.mipmap.anim_5,R.mipmap.anim_6,R.mipmap.anim_7,R.mipmap.anim_8,
//            R.mipmap.anim_9,R.mipmap.anim_10,R.mipmap.anim_11,R.mipmap.anim_12,
//            R.mipmap.anim_13,R.mipmap.anim_14,R.mipmap.anim_15,R.mipmap.anim_16,
//            R.mipmap.anim_17,R.mipmap.anim_18,R.mipmap.anim_19,R.mipmap.anim_20,
//            R.mipmap.anim_21,R.mipmap.anim_22,R.mipmap.anim_23,R.mipmap.anim_24
//    };
//@SuppressLint("HandlerLeak")注解，可以告诉Lint在分析代码时忽略这个警告。
//    @SuppressLint("HandlerLeak")
//    Handler handler = new Handler(){
//        @Override
//        public void handleMessage(@NonNull Message msg) {
//            super.handleMessage(msg);
//            if (msg.what == 123){
//                icon.setImageResource(images[start++%24]);
//            }
//        }
//    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_test);
        icon = this.findViewById(R.id.gifImage);
        icon.setScaleType(ImageView.ScaleType.FIT_XY);
        //handler写在主线程中，作为一个block使用。
        myhandler = new Myhandler(this);
        beginLoop();
        beginChildLoop();

        EditText editText = this.findViewById(R.id.inputLoop);
        TextView textView = this.findViewById(R.id.calcuLoop);
        textView.setClickable(true);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int input = 0;
                if (editText.getText().toString().length() == 0){
                    editText.setText("1000");
                    input = 1000;
                }else {
                    if (Integer.parseInt(editText.getText().toString()) > 2000){
                        input = 2000;
                        editText.setText("2000");
                    }else{
                        input = Integer.parseInt(editText.getText().toString());
                    }
                }

                Message msg = new Message();
                msg.what = 0x234;
                Bundle bundle = new Bundle();
                bundle.putInt(UPPER_NUM ,input);
                msg.setData(bundle);
                // 向新线程中的Handler发送消息
                loop.mhandler.sendMessage(msg);
            }
        });
    }

    void beginLoop(){
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                myhandler.sendEmptyMessage(123);
            }
        },0,200);
    }

    void beginChildLoop(){
        //子线程handler
        loop = new CalLoop(this);
        loop.start();
    }
//在activity内声明一个静态的内部类。避免被外部持有而产生内存泄漏
    private static class Myhandler extends Handler{
        int start = 0;
        HandlerTest test;
        int[] images = new int[]{
                R.mipmap.anim_1,R.mipmap.anim_2,R.mipmap.anim_3,R.mipmap.anim_4,
                R.mipmap.anim_5,R.mipmap.anim_6,R.mipmap.anim_7,R.mipmap.anim_8,
                R.mipmap.anim_9,R.mipmap.anim_10,R.mipmap.anim_11,R.mipmap.anim_12,
                R.mipmap.anim_13,R.mipmap.anim_14,R.mipmap.anim_15,R.mipmap.anim_16,
                R.mipmap.anim_17,R.mipmap.anim_18,R.mipmap.anim_19,R.mipmap.anim_20,
                R.mipmap.anim_21,R.mipmap.anim_22,R.mipmap.anim_23,R.mipmap.anim_24
        };

        private final WeakReference<HandlerTest> mHandler;
        public Myhandler(HandlerTest test){
            mHandler = new WeakReference<HandlerTest>(test);
            this.test = test;
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            HandlerTest handlerTest = mHandler.get();
            if (handlerTest != null){
                if (msg.what == 123){
                    handlerTest.icon.setImageResource(images[start++%24]);
                }
            }
        }
    }

    private static class CalLoop extends Thread{
        HandlerTest test;

        public CalLoop(HandlerTest test){
            this.test = test;
        }

        @SuppressLint("HandlerLeak")
        Handler mhandler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                if(msg.what == 0x234)
                {
                    int upper = msg.getData().getInt(UPPER_NUM);
                    if (upper > 2000){
                        upper = 2000;
                    }
                    List<Integer> nums = new ArrayList<Integer>();
                    // 计算从2开始、到upper的所有质数
                    outer:
                    for (int i = 2 ; i <= upper ; i++)
                    {
                        // 用i处于从2开始、到i的平方根的所有数
                        for (int j = 2 ; j <= Math.sqrt(i) ; j++)
                        {
                            // 如果可以整除，表明这个数不是质数
                            if(i != 2 && i % j == 0)
                            {
                                continue outer;
                            }
                        }
                        nums.add(i);
                    }
                    // 使用Toast显示统计出来的所有质数
                    Toast.makeText(test, nums.toString()
                            , Toast.LENGTH_LONG).show();
                }
            }
        };

        @Override
        public void run() {
            super.run();
            Looper.prepare();
            Looper.loop();
        }
    }
}