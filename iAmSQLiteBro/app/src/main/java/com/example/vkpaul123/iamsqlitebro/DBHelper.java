package com.example.vkpaul123.iamsqlitebro;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by vkpaul123 on 31/1/18.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "studentManager";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + StudentContract.FeedEntry.tableName + " (" +
                    StudentContract.FeedEntry.col1 + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    StudentContract.FeedEntry.col2 + " TEXT," +
                    StudentContract.FeedEntry.col3 + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + StudentContract.FeedEntry.tableName;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
