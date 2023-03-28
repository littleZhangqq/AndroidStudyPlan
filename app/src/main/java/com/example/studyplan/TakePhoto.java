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
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import Util.DialogUtil;
import Util.SweetDialog;

public class TakePhoto extends BaseActivity {
    final int REQUEST_IMAGE_CAPTURE = 1;
    final int REQUEST_ALBUM_IMAGE = 2;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_photo);
        intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        getDatePickerResult();
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
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                            int result =
                                    TakePhoto.this.checkSelfPermission(Manifest.permission.CAMERA);
                            if (result != PackageManager.PERMISSION_GRANTED) {
                                showMissingPermissionDialog();
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
                        Intent intent = new Intent("android.intent.action" +
                                ".GET_CONTENT");
                        intent.setType("image/*");
                        startActivityForResult(intent,REQUEST_ALBUM_IMAGE);
                        sweetDialog.dismiss();
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

        TextView picker = this.findViewById(R.id.touchtopicker);
        picker.setClickable(true);
        picker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SweetDialog sweetDialog =
                        DialogUtil.openCustomDialog(TakePhoto.this,
                                R.layout.date_select,
                                R.style.BottomPushDialogStyle);
                sweetDialog.setOnClickListener(R.id.dateCancel, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sweetDialog.dismiss();
                    }
                });

                sweetDialog.setOnClickListener(R.id.datePickerConsure, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sweetDialog.dismiss();
                    }
                });
                sweetDialog.show();
            }
        });
    }

    void getDatePickerResult(){
        Calendar calendar = Calendar.getInstance();
        View pickerView =
                LayoutInflater.from(TakePhoto.this).inflate(R.layout.date_select,null);
        DatePicker datePicker = pickerView.findViewById(R.id.datePicker);
        datePicker.setMinDate(calendar.getTimeInMillis());
        datePicker.init(calendar.get(Calendar.YEAR-1),
                calendar.get(Calendar.MONTH-1),
                calendar.get(Calendar.DAY_OF_MONTH-1),
                new DatePicker.OnDateChangedListener() {
                    @Override
                    public void onDateChanged(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {
                        TextView tv =
                                TakePhoto.this.findViewById(R.id.pickerResult);
                        tv.setText(year+"-"+ monthOfYear+"-"+ dayOfMonth);
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ImageView imageView = this.findViewById(R.id.photoImage);
//        相机拍照
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){
            assert data != null;
            Bundle extras = data.getExtras();
            Bitmap image = (Bitmap) extras.get("data");
            imageView.setImageBitmap(image);
        }else if (requestCode == REQUEST_ALBUM_IMAGE && resultCode == RESULT_OK){
//            相册选照片
            try {
//                第一行代码获取了从相册中选中的图片的URI地址。
                assert data != null;
                final Uri imageUri = data.getData();
//                第二行代码通过获取相册图片的URI地址，使用ContentResolver的openInputStream方法打开了输入流对象。
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
//                第三行代码使用BitmapFactory.decodeStream方法从输入流中获取Bitmap对象。
                Bitmap bitmap = BitmapFactory.decodeStream(imageStream);
                imageView.setImageBitmap(bitmap);
//                需要注意的是，这段代码应该在Activity或Fragment中使用，并且需要添加try-catch语句来处理可能出现的异常情况。
            } catch (Exception ignored) {
            }
        }
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