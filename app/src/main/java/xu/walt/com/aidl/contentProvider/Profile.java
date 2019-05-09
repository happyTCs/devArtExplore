package xu.walt.com.aidl.contentProvider;

import android.net.Uri;

/**
 * Created by walt on 2019/5/8.
 */

public class Profile {
    /**
     * 8      * 表格名称
     * 9
     */
    public static final String TABLE_NAME = "tablename";

    /**
     * 13      * 列表一，_ID，自动增加
     * 14
     */
    public static final String COLUMN_ID = "_id";

    /**
     * 18      * 列表二，名称
     * 19
     */
    public static final String COLUMN_NAME = "name";

    public static final String AUTOHORITY = "xu.walt.com.aidl.contentProvider.MyProvider";
    public static final int ITEM = 1;
    public static final int ITEM_ID = 2;

    public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.wang.tablename";
    public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.wang.tablename";

    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTOHORITY + "/tablename");
}
