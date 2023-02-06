package com.example.studyplan;

import android.content.Context;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import java.util.List;


public class CalcuAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    private Context mContext;
    public CalcuAdapter(@Nullable List<String> data, Context context) {
        super(R.layout.calculategrid, data);
        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.grid_item,item);
    }
}
