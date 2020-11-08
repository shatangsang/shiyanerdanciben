package com.example.shiyanerdanciben;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class database extends SQLiteOpenHelper {     //对数据库进行创建
    public static  final String CREATE_NOTE = "create table Note("
            + "id integer primary key autoincrement,"
            + "title text not null,"
            + "content text,"
            + "date datetime not null default current_time)";
    private Context mContext;
    public database(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context,name,factory,version);
        mContext = context;
    }
    @Override
    public void  onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_NOTE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }
}