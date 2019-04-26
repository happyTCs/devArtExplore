package xu.walt.com.aidl;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.xml.sax.helpers.LocatorImpl;

import java.io.Serializable;

import xu.walt.com.aidl.activity.SecordActivity;
import xu.walt.com.aidl.bean.Person;
import xu.walt.com.aidl.utils.LogUtil;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null) {
            String extra = savedInstanceState.getString("extra");
            Log.i(TAG, "onCreate: "+extra);
        }
        Person person = new Person();
        person.setName("zhangsan");
        Intent intent = new Intent(this, SecordActivity.class);
        intent.putExtra("person_data",  person);
        startActivity(intent);


    }

    /**
     * git checkout -b dev
     *git add -A
     *git commit -m "branch logutil"
     *git push origin dev:dev
     *
     * @param outState
     */

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
