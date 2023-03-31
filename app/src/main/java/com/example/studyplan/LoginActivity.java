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

import Fragment.AccountLoginFragment;
import Fragment.PhoneLoginFragment;
import Util.DialogUtil;
import Util.SweetDialog;

public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        PhoneLoginFragment phoneLoginFragment = new PhoneLoginFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.loginfragment,
                phoneLoginFragment).commit();
    }
}

