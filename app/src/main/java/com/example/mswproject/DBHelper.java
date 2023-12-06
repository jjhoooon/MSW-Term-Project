package com.example.mswproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "mealDB";
    private static final int DATABASE_VERSION = 1;
    public DBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db){
        String sql = "CREATE TABLE meallist ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "dish String, " +
                "side String, " +
                "category String, " +
                "start_time String," +
                "end_time String, " +
                "cost int, " +
                "place String, " +
                "calories int, " +
                "review String," +
                "photo_path TEXT)";
        db.execSQL(sql);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS meallist");
        onCreate(db);
    }
}