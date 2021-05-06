package com.example.notifymadproject;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    Button btnNotepad;
    Button btnTodo;
    Button btnReminder;
    Button btnProfile;
    private static final int REQUEST_CODE = 12345;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent i = new Intent(this, AlarmBroadcast.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, REQUEST_CODE, i, 0);
        AlarmManager am = (AlarmManager) getSystemService(Activity.ALARM_SERVICE);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.SECOND, 1);

        long frequency = 1 * 1000;
        am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),frequency, pendingIntent);
    }

    public void intentTodo(View view) {
        Intent intentTodo = new Intent(this, Todo.class);
        startActivity(intentTodo);
    }

    public void intentToNotepad(View view) {
        Intent intentToNotes = new Intent(this, NotepadHome.class);
        startActivity(intentToNotes);
    }

    public void intentToProfile(View view) {
        Intent intentToProfile = new Intent(this, profileHome.class);
        startActivity(intentToProfile);
    }

    public void intentToReminder(View view) {
        Intent intentToReminder = new Intent(this, ActivityReminder_01.class);
        startActivity(intentToReminder);
    }

    protected int multiplyNumbers(int x, int y) {
        return x * y;
    }
}