package com.example.notifymadproject.Database;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1; // database version
    private static final String DATABASE_NAME = "reminder_db"; // database name
    private static final String TABLE_NAME = "Event"; // table name

    // table columns
    public  static final String ID = "id";
    public  static final String EVENT = "event";
    public  static final String DATE = "date";
    public  static final String TIME = "time";

    private SQLiteDatabase sqLiteDatabase;

    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("+ ID +
            " INTEGER PRIMARY KEY AUTOINCREMENT," + EVENT + " TEXT NOT NULL," + DATE + " TEXT NOT NULL," +
            TIME + " TEXT NOT NULL);";

    public DatabaseHelper (Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // add event to database
    public void createEvent(EventModel eventModel) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.EVENT, eventModel.getEvent());
        contentValues.put(DatabaseHelper.DATE, eventModel.getDate());
        contentValues.put(DatabaseHelper.TIME, eventModel.getTime());

        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.insert(DatabaseHelper.TABLE_NAME, null, contentValues);
    }

    // get all events
    public List<EventModel> getAllEvents() {
        String sql = "SELECT * FROM " + TABLE_NAME;
        sqLiteDatabase = this.getReadableDatabase();
        List<EventModel> eventModelList = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);

        if (cursor.moveToFirst()) {
            do {
                int id = Integer.parseInt(cursor.getString(0));
                String event = cursor.getString(1);
                String date = cursor.getString(2);
                String time = cursor.getString(3);
                eventModelList.add(new EventModel(id,event,date,time));
            }while (cursor.moveToNext());
        }

        cursor.close();
        return eventModelList;
    }

    // update data
    public void updateData(EventModel model) {
        String id = String.valueOf(model.getId());
        sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(EVENT, model.getEvent());
        contentValues.put(DATE, model.getDate());
        contentValues.put(TIME, model.getTime());

        long result = sqLiteDatabase.update(TABLE_NAME, contentValues, "id=?", new String[]{id});
    }

    // delete data
    public void deleteData(String id) {
        sqLiteDatabase = this.getWritableDatabase();
        long result = sqLiteDatabase.delete(TABLE_NAME, "id=?", new String[]{id});

    }
}
