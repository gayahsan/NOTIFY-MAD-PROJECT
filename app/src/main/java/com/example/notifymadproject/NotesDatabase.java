package com.example.notifymadproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class NotesDatabase extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "noteify1";
    private static final String DATABASE_TABLE = "notes_tb";
    private static long ID;
    private static NotesDataBridge note;

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
        String query = "CREATE TABLE "+ DATABASE_TABLE + "("+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+
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

    public NotesDataBridge getNote(long ID) {
        // SELECT * FROM DATABASE_TABLE WHERE id = ID;
        SQLiteDatabase database = this.getReadableDatabase();
        String[] columns = new String[] {KEY_ID, KEY_TITLE, KEY_CONTENT, KEY_DATE, KEY_TIME};

        // Query
        Cursor cursor = database.query(DATABASE_TABLE, columns, KEY_ID+"=?", new String[]{String.valueOf(ID)}, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        try {
            assert cursor != null; // Recommended Coding Convention from IDEA
            note = new NotesDataBridge(cursor.getLong(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return note;
    }

    public List<NotesDataBridge> getNotes() {
        SQLiteDatabase database = this.getReadableDatabase();
        List<NotesDataBridge> allNotes = new ArrayList<>();

        // SELECT * FROM DATABASE_TABLE;
        String query = "SELECT * FROM "+DATABASE_TABLE;
        Cursor cursor = database.rawQuery(query, null);

        try {
            if (cursor.moveToFirst()) {
                do {
                    NotesDataBridge note = new NotesDataBridge();
                    note.setId(cursor.getLong(0));
                    Log.d("ID", "ID: " + cursor.getLong(0));
                    note.setTitle(cursor.getString(1));
                    note.setContent(cursor.getString(2));
                    note.setDate(cursor.getString(3));
                    note.setTime(cursor.getString(4));

                    allNotes.add(note);
                } while (cursor.moveToNext());
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        try {
            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return allNotes;
    }
}