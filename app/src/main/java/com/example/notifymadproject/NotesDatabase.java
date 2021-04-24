package com.example.notifymadproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;
import android.util.Log;

public class NotesDatabase extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "noteify";
    private static final String DATABASE_TABLE = "notes_tb";
    long ID;

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
        String query = "CREATE TABLE "+ DATABASE_TABLE + "("+ KEY_ID+ " INT PRIMARY KEY,"+
                KEY_TITLE + " TEXT,"+
                KEY_CONTENT + " TEXT,"+
                KEY_DATE + " TEXT,"+
                KEY_TIME + " TEXT"+")";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion >= newVersion) {
            return;
        }
        db.execSQL("DROP TABLE IF EXISTS " +
                DATABASE_TABLE);
    }

    public long addNote(NotesDataBridge note) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();

            // Set values to the key;
            contentValues.put(KEY_TITLE, note.getTitle());
            contentValues.put(KEY_CONTENT, note.getContent());
            contentValues.put(KEY_DATE, note.getDate());
            contentValues.put(KEY_TIME, note.getTime());

            // Inserting to the database;
            ID = db.insert(DATABASE_TABLE, null, contentValues);

            // Checking if the data is inserted;
            Log.d("addNote", "ID => " + ID);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ID;
    }
}