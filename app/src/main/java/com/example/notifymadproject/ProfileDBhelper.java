package com.example.notifymadproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ProfileDBhelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "todaycontact.db";
    public static final int DATABASE_VERSION = 1;

    public ProfileDBhelper( Context context) { super(context, DATABASE_NAME, null, DATABASE_VERSION); }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_TABLE = "CREATE TABLE " + ProfileContract.ContactEntry.TABLE_NAME +  " ("
                + ProfileContract.ContactEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," // THIS AUTOMATICALLY INCREMENTS THE ID BY 1
                + ProfileContract.ContactEntry.COLUMN_NAME + " TEXT NOT NULL, "
                + ProfileContract.ContactEntry.COLUMN_EMAIL + " TEXT NOT NULL, "
                + ProfileContract.ContactEntry.COLUMN_PHONENUMBER + " TEXT NOT NULL, "
                + ProfileContract.ContactEntry.COLUMN_TYPEOFCONTACT + " TEXT NOT NULL, "
                + ProfileContract.ContactEntry.COLUMN_PICTURE  + " TEXT);";

        db.execSQL(SQL_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        
    }
}
