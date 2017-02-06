package com.example.jcao5.sizebook;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by jcao5 on 2017/2/6.
 */

//create the database
public class MyDbOpenHelper extends SQLiteOpenHelper {

    public static String dbName="Size.db";//the name of database
    public static int dbVersion=2;//vision of database

    public MyDbOpenHelper(Context context) {
        super(context, dbName, null, dbVersion);
    }

    //create the table
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists SizeBook(_id INTEGER primary key," +
                "name text,date varchar(100),neck varchar(20),bust vartchar(20),chest varchar(20),waist varchar(20),hip varchar(20),inseam varchar(20),comment varchar(20) )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
