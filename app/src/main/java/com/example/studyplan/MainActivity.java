package com.example.studyplan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;

import Model.News;


public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private MainAdapter mAdapter;
    List<News> mNewsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerview);
        String[] titles = {"总纲-列表应用，跳转，导航设置","计算器-布局-button点击，屏幕适配",
                "图片浏览器-相册调用，权限，拍照，选择照片等","登录页面-输入框，文本框，富文本，webview，单选复选按钮",
                "日期选择器，时间选择器，区域选择器，弹框提示框","handler的消息传递。监听，回调，代理，事件，通知",
                "service","broadcastReceiver","contentprovider",
                "数据存储，读取，数据库，tmp，等","网络，数据解析，转模型。上传，下载，post，put，delete，get" +
                "，upload，download，等","webview","贝塞尔曲线，动画","定位，蓝牙","打包","签名",
                "反编译","三方"};
        // 构造一些数据
        for (int i = 0; i < titles.length; i++) {
            News news = new News();
            news.title = titles[i];
            news.content = "";
            mNewsList.add(news);
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new MainAdapter(mNewsList,this);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter,
                                    @NonNull View view, int position) {
                /*
                1:Intent intent = new Intent(this,CalculateActivity.class);
                这么写会报错，因为这是写在onclick中，这里使用的this，代表了lisener，不是activity,
                所以必须使用MAinActivity.this

                2:intent跳转到目标activity时，必须现在mainfest文件中将该activity注册，否则会崩溃
                * */
                Log.e("main","点击了第"+position+"个");
                switch (position){
                    case 0:
                        Toast.makeText(MainActivity.this, "点击了第0个，无效果",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Intent intent = new Intent(MainActivity.this,CalculateActivity.class);
                        startActivity(intent);
                        break;
                }

            }
        });
    }

}
