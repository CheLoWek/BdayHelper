package com.lowek.che.bdayhelper.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper{
    public DBHelper(Context context) {
        super(context, "bdayhelper_contacts", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table bdayhelper_contacts " +
                "(" +
                "id integer primary key autoincrement," +
                "name text," +
                "surname text," +
                "birthday text," +
                "picture text," +
                "phone text," +
                "present text," +
                "has_present integer," +
                "origin text" +
                ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
