package com.example.studyplan;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

public class CalculateActivity extends BaseActivity{
    private RecyclerView rv;
    public List<String> list = new ArrayList<>();
    private String lastNum;
    private String nextNum;
    private boolean prepare;
    private int type;//1加 2减 3乘 4除 0无状态
    private StringBuilder sb;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculatesimulator);
        String[] strs = {"1","2","3","4","5","6","7","8","9","0","c","+",
                "-","X","÷","="};
        for (String obj :strs){
            list.add(obj);
        }
        GridView gridView = findViewById(R.id.channel);
        TextView tv = findViewById(R.id.resultLabel);
        MyAdapter adapter = new MyAdapter(this,list);
        gridView.setAdapter(adapter);
        sb = new StringBuilder();
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                calculateResult(position,tv);
            }
        });
    }

    void calculateResult(int position,TextView tv){
        if (position<10){
            sb.append(list.get(position));
            tv.setText(sb.toString());
            if (!prepare ){
                lastNum = sb.toString();
            }else {
                nextNum = sb.toString();
            }
        }else if (position > 10 && position < 15){
            prepare = true;
            type = position-10;
            sb = new StringBuilder();
        }else if (position == 15){
            int result = 0;
            prepare = false;
            sb = new StringBuilder();
            if (type == 1){
                result =
                        Integer.parseInt(lastNum)+Integer.parseInt(nextNum);
            }else if (type == 2){
                result =
                        Integer.parseInt(lastNum)-Integer.parseInt(nextNum);
            }else if (type == 3){
                result =
                        Integer.parseInt(lastNum)*Integer.parseInt(nextNum);
            }else if (type == 4){
                result =
                        Integer.parseInt(lastNum)/Integer.parseInt(nextNum);
            }
            tv.setText(String.valueOf(result));
        }else if (position == 10){
            tv.setText("0");
            prepare = false;
            sb = new StringBuilder();
            lastNum = "";
            nextNum = "";
        }
    }
}

class MyAdapter extends BaseAdapter {
    private Context mcontext;
    private LayoutInflater layoutInflater;
    private List<String > list;

    MyAdapter(Context context,List<String> list) {
        this.mcontext = context;
        layoutInflater = LayoutInflater.from(context);
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = layoutInflater.inflate(R.layout.calculategrid,null);
        TextView tv = v.findViewById(R.id.grid_item);
        tv.setText(list.get(position));
        return v;
    }
}