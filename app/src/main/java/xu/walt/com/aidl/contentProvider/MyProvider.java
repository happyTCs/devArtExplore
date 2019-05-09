package xu.walt.com.aidl.contentProvider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by walt on 2019/5/8.
 */

public class MyProvider extends ContentProvider {
    DBHelper mDbHelper = null;
    SQLiteDatabase db = null;
    private static final UriMatcher mMatcher;

    static {
        mMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        mMatcher.addURI(Profile.AUTOHORITY, Profile.TABLE_NAME, Profile.ITEM_ID);
    }

    @Override
    public boolean onCreate() {
        mDbHelper = new DBHelper(getContext());
        db = mDbHelper.getReadableDatabase();
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Cursor c = null;
        switch (mMatcher.match(uri)) {
            case Profile.ITEM:
                c = db.query(Profile.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case Profile.ITEM_ID:
                c = db.query(Profile.TABLE_NAME, projection, Profile.COLUMN_ID + "=" + uri.getLastPathSegment(), selectionArgs, null, null, sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI" + uri);
        }
        c.setNotificationUri(getContext().getContentResolver(), uri);
        return c;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (mMatcher.match(uri)) {
            case Profile.ITEM:
                return Profile.CONTENT_TYPE;
            case Profile.ITEM_ID:
                return Profile.CONTENT_ITEM_TYPE;
            default:
                return new IllegalArgumentException("Unknown URI" + uri).toString();
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        long rowId;
        if (mMatcher.match(uri) != Profile.ITEM) {
            throw new IllegalArgumentException("Unknown URI" + uri);
        }
        rowId = db.insert(Profile.TABLE_NAME, null, values);
        if (rowId > 0) {
            Uri noteUri = ContentUris.withAppendedId(Profile.CONTENT_URI, rowId);
            getContext().getContentResolver().notifyChange(noteUri, null);
            return noteUri;
        }
        throw new SQLException("Failed to insert row into " + uri);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
