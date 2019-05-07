package xu.walt.com.aidl;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.PersistableBundle;
import android.os.SystemClock;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.xml.sax.helpers.LocatorImpl;

import java.io.Serializable;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import xu.walt.com.aidl.activity.SecordActivity;
import xu.walt.com.aidl.bean.Person;
import xu.walt.com.aidl.broadcastReceiver.ScreenControlAlarmReceiver;
import xu.walt.com.aidl.utils.LogUtil;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private static final long INTERVAL = 3 * 1000;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //1、恢复activity
//        initInstanceState(savedInstanceState);
        //2、序列化数据
//        initPacel();
        //3、timer
//        initTimer();
        //4、Alarm机制 写在service里
//        initAlarm();
        //4.2 写在broadcast 里
        initBroadCastAlarm();


    }

    private void initBroadCastAlarm() {
       AlarmManager alarmManager= (AlarmManager) getApplicationContext().getSystemService(ALARM_SERVICE);
       Intent alarmIntent =new Intent(getApplicationContext(), ScreenControlAlarmReceiver.class)
               .setAction("intent_alarm_log");
        PendingIntent pendingIntent= PendingIntent.getBroadcast(getApplicationContext(),0
                    ,alarmIntent,0);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,   System.currentTimeMillis() ,INTERVAL, pendingIntent);
        } else {
            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,  SystemClock.elapsedRealtime(),INTERVAL-1000, pendingIntent);
        }

        /**
         * 3.取消闹钟
         只需要取消同一个PendingIntent 即可。

         AlarmManager alarmService = (AlarmManager) context.getSystemService(ALARM_SERVICE);
         Intent alarmIntent = new Intent(context, ScreenControlAlarmReceiver.class).setAction("intent_alarm_log");
         PendingIntent broadcast = PendingIntent.getBroadcast(context, 0, alarmIntent, 0);
         alarmService.cancel(broadcast);
         **/
    }

    private void initAlarm() {

        Intent intent = new Intent(getApplicationContext(),LongRunningService.class);
        startService(intent);
    }

    private void initInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            String extra = savedInstanceState.getString("extra");
            Log.i(TAG, "onCreate: "+extra);
        }
    }

    private void initPacel() {
        Person person = new Person();
        person.setName("zhangsan");
        Intent intent = new Intent(this, SecordActivity.class);
        intent.putExtra("person_data",  person);
        startActivity(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void initTimer() {
        Timer timer = new Timer(true);
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                //需要执行的代码
            }
        };
        //以下是几种调度方法
        long time =22;
        long time2 =System.currentTimeMillis();
        Date date = new Date(time2);
        long period=22;
        //firstTime为Date类型,period为long，表示从firstTime时刻开始，每隔period毫秒执行一次。
        timer.schedule(task,date,period);
        //delay为long,period为long：从现在起过delay毫秒以后，每隔period毫秒执行一次。
        timer.schedule(task,time2,period);
    }



//    @Override
//    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
//        super.onSaveInstanceState(outState, outPersistentState);
//
//        Log.i(TAG, "onSaveInstanceState: ");
//        outState.putString("extra","test");
//    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG, "onSaveInstanceState: ");
        outState.putString("extra","test");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String extra = savedInstanceState.getString("extra");
        Log.i(TAG, "onRestoreInstanceState: "+extra);
        LogUtil.i(TAG,extra);

    }
}
