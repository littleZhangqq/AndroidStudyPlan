package com.example.studyplan;


import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import Model.News;

public class CalculateActivity extends AppCompatActivity {
    private CalcuAdapter mAdapter;
    private RecyclerView rv;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculatesimulator);
        List<String> list = new ArrayList<>();
        String[] strs = {"1","2","3","4","5","6","7","8","9","0","c","+",
                "-","X","÷","="};
        for (String obj :strs){
            list.add(obj);
        }
        /*
        * 九宫格布局，列表，都用recyclerview做，然后在设置布局管理器的时候区分layoutmanager就可以，这个三方还是很好用的
        * */
        GridLayoutManager layoutManager = new GridLayoutManager(this,4);
        rv = findViewById(R.id.calcuGrid);
        rv.setLayoutManager(layoutManager);
        mAdapter = new CalcuAdapter(list,this);
        rv.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter,
                                    @NonNull View view, int position) {
                Log.e("","点击了符号："+ list.get(position) + "");
            }
        });
    }
}
