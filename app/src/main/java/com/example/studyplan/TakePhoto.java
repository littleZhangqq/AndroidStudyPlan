package com.example.studyplan;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import Util.DialogUtil;
import Util.SweetDialog;

public class TakePhoto extends AppCompatActivity {

    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_photo);
        intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        TextView textView = this.findViewById(R.id.touchTophoto);
        textView.setClickable(true);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SweetDialog sweetDialog =
                        DialogUtil.openCustomDialog(TakePhoto.this,
                                R.layout.default_actionsheet,
                                R.style.BottomPushDialogStyle);
                sweetDialog.setOnClickListener(R.id.takephoto,
                        new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final int REQUEST_IMAGE_CAPTURE = 1;
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                            int result =
                                    TakePhoto.this.checkSelfPermission(Manifest.permission.CAMERA);
                            if (result != PackageManager.PERMISSION_GRANTED) {
                                showMissingPermissionDialog();
                                return;
                            }else{
                                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                                sweetDialog.dismiss();
                            }
                        }else{
                            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                            sweetDialog.dismiss();
                        }
                    }
                });
                sweetDialog.setOnClickListener(R.id.takealbum,
                        new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                sweetDialog.setOnClickListener(R.id.btn_cancel,
                        new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sweetDialog.dismiss();
                    }
                });
                sweetDialog.show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

    private void showMissingPermissionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示");
        builder.setMessage("当前应用缺少相机权限。\n请点击\"设置\"-\"权限\"-打开相机权限。");

        // 拒绝, 退出应用
        builder.setNegativeButton("取消",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        finish();
                    }
                });

        builder.setPositiveButton("设置",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        isNeedCheck = true;
                        //todo 更改权限选择方式
//                        startAppSettings();
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        Uri uri = Uri.fromParts("package", getPackageName(), null);
                        intent.setData(uri);
                        startActivity(intent);
                    }
                });

        builder.setCancelable(false);

        builder.show();
    }
}