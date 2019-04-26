package xu.walt.com.aidl.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.Serializable;

import xu.walt.com.aidl.R;
import xu.walt.com.aidl.bean.Person;

/**
 * Created by walt on 2019/4/24.
 */

public class SecordActivity extends Activity {
    private static final String TAG = "SecordActivity";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secord);
       Person person_data = (Person) getIntent().getSerializableExtra("person_data");
        Log.i(TAG, "onCreate: "+person_data.getName());
    }
}
