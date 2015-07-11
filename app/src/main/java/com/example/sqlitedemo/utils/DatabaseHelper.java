package com.example.sqlitedemo.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by XU on 2015/7/11.
 */
public class DatabaseHelper extends SQLiteOpenHelper{

    private static final String name = "contacts.db"; //数据库名称
    private static final int version = 1;             //数据库版本

    public DatabaseHelper(Context context) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table t_persons(name varchar(20) primary key, phone varchar(20));";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
