package com.example.studyplan;

import android.content.Context;
import android.widget.TextView;

import androidx.annotation.Nullable;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import java.util.List;

import Model.News;

public class MainAdapter extends BaseQuickAdapter<News, BaseViewHolder> {

    private Context mContext;
    public MainAdapter(@Nullable List<News> data, Context context) {
        super(R.layout.item_list, data);
        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, News item) {
        helper.setText(R.id.textView,item.title);
    }

}


