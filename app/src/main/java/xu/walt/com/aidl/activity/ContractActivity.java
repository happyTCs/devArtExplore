package xu.walt.com.aidl.activity;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import xu.walt.com.aidl.R;

/**
 * Created by walt on 2019/5/9.
 */

public class ContractActivity extends Activity {
    Button button = null;
    List<String> contactsList = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    ListView contactsView = null;
    ContentResolver contentResolver = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contract);

        button = (Button) findViewById(R.id.buttonShowContacts);
        button.setOnClickListener(listener);

        contactsView = (ListView) findViewById(R.id.contactsList);
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, contactsList);
        contactsView.setAdapter(adapter);

        contentResolver = getContentResolver(); // 获取ContentResolver对象
    }

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.buttonShowContacts:
                    showContacts();
                    break;
            }
        }
    };

    private void showContacts() {
        /*
         * 调用getContentResolver()方法获取ContentResolver对象，并且调用query方法，
         * 传入联系人的Uri，来查询联系人信息，类似于数据库的查询方法。
         */
        Cursor cursor = contentResolver.query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null, null, null, null);
        if (cursor.moveToFirst()) {
            contactsList.clear();
            do {
                /*
                 * 获取联系人姓名
                 */
                String str = "姓名：" + cursor.getString(cursor.getColumnIndex(
                        ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)) + "\n";
                /*
                 * 获取联系人电话号码
                 */
                str += "电话：" + cursor.getString(cursor.getColumnIndex(
                        ContactsContract.CommonDataKinds.Phone.NUMBER));
                contactsList.add(str);
            } while (cursor.moveToNext());
        }
        cursor.close();
        adapter.notifyDataSetChanged(); // 适配器发送更改后的数据
    }

}
