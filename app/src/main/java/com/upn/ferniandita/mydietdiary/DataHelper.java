package com.upn.ferniandita.mydietdiary;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Stefanus on 21/05/2017.
 */

public class DataHelper extends SQLiteOpenHelper {
    //Membuat database awal
    private static final String DB_NAME = "mydietdiary.db";
    private static final int DB_VER = 1;

    //Membuat database
    public DataHelper(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //Membuat table user
        String sql = "create table user(no integer primary key autoincrement,username text,password text null,email text null, fullname text null, flag int null);";
        db.execSQL(sql);

        //Membuat table diagnose
        sql = "create table diagnose(no integer primary key autoincrement, " +
                "username text null, weight integer null, height integer null, " +
                "age integer null, gender text null, bloodtype text null," +
                "maag text null, hypertension text null, diabetes text null," +
                "gastritis text null, cholesterol text null," +
                "foreign key (username) references user(username));";
        db.execSQL(sql);

        sql = "create table weightuser(no integer primary key autoincrement," +
                "username text null, weight integer null," +
                "foreign key (username) references user(username))";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
