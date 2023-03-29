package Util;


import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class ServiceTest extends Service {
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("","创建");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d("","绑定");
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("","启动");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("","销毁");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d("","解绑");
        return super.onUnbind(intent);
    }
}

 class ServiceIntentTest extends IntentService{

     /**
      * Creates an IntentService.  Invoked by your subclass's constructor.
      *
      * @param name Used to name the worker thread, important only for debugging.
      */
     public ServiceIntentTest(String name) {
         super(name);
     }

     @Override
    protected void onHandleIntent(@Nullable Intent intent) {

    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return super.onBind(intent);
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }
}