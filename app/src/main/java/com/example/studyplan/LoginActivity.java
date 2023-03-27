package com.example.studyplan;

import androidx.annotation.NonNull;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import Util.DialogUtil;
import Util.SweetDialog;

public class LoginActivity extends BaseActivity implements CompoundButton.OnCheckedChangeListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        stringComponent();
        handleEventClick();
    }

    private void handleEventClick(){
        TextView textView = this.findViewById(R.id.getVercode);
        textView.setClickable(true);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAG", "onClick: 点击获取验证码");
                Toast.makeText(LoginActivity.this, "点击获取验证码",
                        Toast.LENGTH_SHORT).show();
            }
        });

        TextView textView1 = this.findViewById(R.id.accountLogin);
        textView1.setClickable(true);
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAG", "onClick: 点击账号登录");
                Toast.makeText(LoginActivity.this, "点击账号登录",
                        Toast.LENGTH_SHORT).show();
            }
        });

        TextView textView2 = this.findViewById(R.id.wechatLogin);
        textView2.setClickable(true);
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAG", "onClick: 点击微信登录");
                Toast.makeText(LoginActivity.this, "点击微信登录",
                        Toast.LENGTH_SHORT).show();
            }
        });

        TextView textView3 = this.findViewById(R.id.phoneLogin);
        textView3.setClickable(true);
        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAG", "onClick: 点击手机号登录");
                Toast.makeText(LoginActivity.this, "点击手机号登录",
                        Toast.LENGTH_SHORT).show();
            }
        });

        CheckBox checkBox = this.findViewById(R.id.privacyCheck);
        checkBox.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (buttonView.isChecked()){
            Toast.makeText(LoginActivity.this, "选中",
                    Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(LoginActivity.this, "未选中",
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void stringComponent(){
        SpannableString string = new SpannableString("登录即同意《软件服务协议》与《隐私政策》");

        //设置点击事件
        NolineSpanClickble clickableSpan = new NolineSpanClickble() {
            @Override
            public void onClick(@NonNull View widget) {
                Log.d("","点击了软件服务协议");
                Toast.makeText(LoginActivity.this, "点击了软件服务协议",
                        Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this,LoadWebView.class);
                startActivity(intent);
            }
        };
        NolineSpanClickble clickableSpan1 = new NolineSpanClickble() {
            @Override
            public void onClick(@NonNull View widget) {
                Log.d("","点击了隐私政策");
                Toast.makeText(LoginActivity.this, "点击了隐私政策",
                        Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this,LoadWebView.class);
                startActivity(intent);
            }
        };
        string.setSpan(clickableSpan,5,13,Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        string.setSpan(clickableSpan1,14,20,Spanned.SPAN_INCLUSIVE_INCLUSIVE);

        //设置文字前景色
        ForegroundColorSpan foregroundColorSpan =
                new ForegroundColorSpan(Color.parseColor("#4968FF"));
        ForegroundColorSpan foregroundColorSpan1 =
                new ForegroundColorSpan(Color.parseColor("#4968FF"));
        string.setSpan(foregroundColorSpan,5,13,
                Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        string.setSpan(foregroundColorSpan1,14,20,
                Spannable.SPAN_INCLUSIVE_INCLUSIVE);

        TextView tv = findViewById(R.id.privacyLabel);
        tv.setText(string);
        tv.setMovementMethod(LinkMovementMethod.getInstance());
    }

}

//去掉超链接文字的下划线
class NolineSpanClickble extends ClickableSpan{
    @Override
    public void updateDrawState(@NonNull TextPaint ds) {
        super.updateDrawState(ds);
        ds.setUnderlineText(false);
    }

    @Override
    public void onClick(@NonNull View widget) {

    }
}