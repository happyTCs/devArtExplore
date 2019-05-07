package xu.walt.com.aidl.broadcastReceiver;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.SystemClock;

import java.text.SimpleDateFormat;
import java.util.Date;

import xu.walt.com.aidl.utils.LogUtil;

import static android.content.Context.ALARM_SERVICE;

/**
 * Created by walt on 2019/5/7.
 */

public class ScreenControlAlarmReceiver extends BroadcastReceiver {
    private static final String TAG = "ScreenControlAlarmRecei";
    @Override
    public void onReceive(Context context, Intent intent) {
        //你的逻辑处理
        //如果需要实现间隔定时器功能，在重新执行1的发送步骤，实现间隔定时，间隔时间为INTERVAL
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        LogUtil.i(TAG,"ScreenControlAlarmReceiver"+df.format(new Date()));


    }
}
