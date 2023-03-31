package Util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyBroadCast extends BroadcastReceiver {
/*
* 不要在广播里添加过多逻辑或者进行任何耗时操作,因为在广播中是不允许开辟线程的, 当onReceiver( )方法运行较长时间(超过10秒)还没有结束的话,那么程序会报错(ANR), 广播更多的时候扮演的是一个打开其他组件的角色,比如启动Service,Notification提示, Activity等！
 * */
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("TAG", "onReceive:Message");

        if (intent.getAction().equals("loginOther")){
            Toast.makeText(context, "应用内自定义通知", Toast.LENGTH_LONG).show();
        }else if (intent.getAction().equals("testBroadCast")){
            Toast.makeText(context, "全局通知", Toast.LENGTH_LONG).show();
        }
    }
}
