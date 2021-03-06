package com.example.vkpaul123.mystudentsmarksfragbro;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by vkpaul123 on 7/3/18.
 */



public class DBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "studentMarks";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + StudentContract.FeedEntry.tableName + " (" +
                    StudentContract.FeedEntry.col1 + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    StudentContract.FeedEntry.col2 + " TEXT," +
                    StudentContract.FeedEntry.col3 + " TEXT," +
                    StudentContract.FeedEntry.col4 + " REAL," +
                    StudentContract.FeedEntry.col5 + " REAL," +
                    StudentContract.FeedEntry.col6 + " REAL," +
                    StudentContract.FeedEntry.col7 + " REAL," +
                    StudentContract.FeedEntry.col8 + " REAL," +
                    StudentContract.FeedEntry.col9 + " REAL)";

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
