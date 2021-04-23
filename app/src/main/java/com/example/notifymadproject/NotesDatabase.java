package com.example.notifymadproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class NotesDatabase extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "noteify";
    private static final String DATABASE_TABLE = "notes_tb";

    // Column names for the database: ID, Title, Content, Time and Date;
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_CONTENT = "content";
    private static final String KEY_DATE = "date";
    private static final String KEY_TIME = "time";

    NotesDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // CREATE TABLE notes (id INT PRIMARY KEY, title TEXT, content TEXT, date TEXT, time TEXT);
        // String query = "CREATE TABLE " + DATABASE_TABLE + "(" + KEY_ID + "INT PRIMARY KEY," + KEY_TITLE + "TEXT," + KEY_CONTENT + "TEXT," + KEY_DATE + "TEXT," + KEY_TIME + "TEXT" + ")";
        String query = "CREATE TABLE IF NOT EXISTS " + DATABASE_TABLE + "(" +
                KEY_ID + "INT NOT NULL PRIMARY KEY AUTOINCREMENT," +
                KEY_TITLE + "TEXT NOT NULL," +
                KEY_CONTENT + "TEXT NOT NULL," +
                KEY_DATE + "TEXT NOT NULL," +
                KEY_TIME + "TEXT NOT NULL" + ")";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion >= newVersion) {
            return;
        }
        db.execSQL("DROP TABLE IF EXISTS " +
                DATABASE_TABLE);


        /*if (condition){
            // execute this one
        } else {
            // execute this one
        }

        ============================

        if (condition)
            // execute this*/
    }
}