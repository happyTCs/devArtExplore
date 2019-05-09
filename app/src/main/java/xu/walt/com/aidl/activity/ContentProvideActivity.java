package xu.walt.com.aidl.activity;

import android.app.ListActivity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.Menu;

import xu.walt.com.aidl.contentProvider.Profile;

/**
 * Created by walt on 2019/5/8.
 */

public class ContentProvideActivity extends ListActivity {
    private SimpleCursorAdapter adapter = null;
    private Cursor mCursor = null;
    private ContentResolver mContentResolver = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initAdapter();
    }

    private void initData() {
        mContentResolver = getContentResolver();
        for (int i = 0; i < 100; i++) {
            ContentValues values = new ContentValues();
            values.put(Profile.COLUMN_NAME, "Wang " + i);
            mContentResolver.insert(Profile.CONTENT_URI, values);
        }
    }

    private void initAdapter() {
        mCursor = mContentResolver.query(Profile.CONTENT_URI, new String[]{Profile.COLUMN_ID, Profile.COLUMN_NAME}, null, null, null);

        startManagingCursor(mCursor);

        //设置adapter
        adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2, mCursor, new String[]{Profile.COLUMN_ID, Profile.COLUMN_NAME}, new int[]{android.R.id.text1, android.R.id.text2});
        setListAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
}
