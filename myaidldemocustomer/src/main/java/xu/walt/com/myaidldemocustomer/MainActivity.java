package xu.walt.com.myaidldemocustomer;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import xu.walt.com.aidl.IMyAidlInterface;


public class MainActivity extends AppCompatActivity {

    private TextView tv;
    IMyAidlInterface mStub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.tv);
        Intent intent = new Intent();
        //由于是隐式启动Service 所以要添加对应的action，A和之前服务端的一样。
        intent.setAction("xu.walt.com.aidl.MyService");
        //android 5.0以后直设置action不能启动相应的服务，需要设置packageName或者Component。
        intent.setPackage("xu.walt.com.aidl");
        bindService(intent,serviceConnection,BIND_AUTO_CREATE);
    }


    private ServiceConnection serviceConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //调用asInterface()方法获得IMyAidlInterface实例
            mStub = IMyAidlInterface.Stub.asInterface(service);
            if (mStub == null) {
                Log.e("MainActivity", "the mStub is null");
            } else {        //当mStub不为空就调用其add方法进行计算，并显示到TextView上面。
                try {
                    int value = mStub.add(1, 8);
                    tv.setText(value + "");
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(serviceConnection);
    }
}
