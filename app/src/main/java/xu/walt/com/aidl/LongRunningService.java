package xu.walt.com.aidl;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.Date;

import xu.walt.com.aidl.utils.LogUtil;

/**https://www.xuebuyuan.com/3122580.html
 * Created by walt on 2019/4/26.
         * 需要注意的是，从Android4.4开始，Alarm任务的触发时间将会变得不准确，有可能会延迟一段时间后任务才能执行，
         * 这是系统在耗电性方面的优化，系统会自动检测目前有多少Alarm任务存在，然后将触发时间相近的几个任务放在一起执行，
         * 这样就可以大幅度减少CPU被唤醒的次数，从而减少耗电量。
 如果要求Alarm任务的执行时间必须准确无误，只需要使用AlarmManager的setExact()方法来替代set()方法。就基本上可以保证任务能够准时执行了。
 */

public class LongRunningService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogUtil.i("LongRunningService","LongRunningService");
        new Thread(new Runnable() {
            @Override
            public void run() {
                //在这里执行具体逻辑
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
                LogUtil.i("LongRunningService","run:"+df.format(new Date()));
            }
        }).start();

        AlarmManager alarmManager = (AlarmManager) getSystemService(getApplicationContext().ALARM_SERVICE);
        int anHour = 5*1000;
        long triggerAtTime = SystemClock.elapsedRealtime() + anHour;
        Intent i = new Intent(this, LongRunningService.class);
        PendingIntent pi = PendingIntent.getService(this,0,i,0);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            alarmManager.setExact(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, pi);
        } else {
            alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, pi);
        }
        return super.onStartCommand(intent, flags, startId);
    }
}
