package com.lowek.che.bdayhelper.utils;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseMethods {

    public static Cursor getDataNoSort(SQLiteDatabase db, String databaseName) {
        Cursor cursor = db.query(databaseName, null, null, null, null, null, null);
        return cursor;
    }

//    public static Cursor getDataBirthdaySort(SQLiteDatabase db, String databaseName) {
//        Cursor cursor = db.query(databaseName, null, null, null, null, null, "date(birthday)");
//        return cursor;
//    }

//    public static  Cursor getDataVK(SQLiteDatabase db, String databaseName){
//
//    }

}
