package com.example.notifymadproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.notifymadproject.Database.DatabaseHelper;
import com.example.notifymadproject.Database.EventModel;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Activity_reminder_create_new extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "Activity_reminder";
    // views
    private EditText edt_event, edt_date, edt_time;
    private ImageView ic_calendar, ic_time, btn_done;

    // data members
    private String timeToNotify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder_create_new);

        // hooks
        edt_event = findViewById(R.id.edt_up_event);
        edt_date = findViewById(R.id.edt_up_date);
        edt_time = findViewById(R.id.edt_up_time);
        ic_calendar = findViewById(R.id.ic_calendar);
        ic_time = findViewById(R.id.ic_time);
        btn_done = findViewById(R.id.btn_update);

        // event listners
        ic_calendar.setOnClickListener(this);
        ic_time.setOnClickListener(this);
        btn_done.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == ic_time)
            selectTime();
        else if (v == ic_calendar)
            selectDate();
        else if (v == btn_done)
            addEvent();
    }

    // show calendar
    private void selectDate() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                edt_date.setText(dayOfMonth + "-" + (month + 1) + "-" + year);
            }
        },year,month,day);
        dialog.show();
    }

    // show clock
    private void selectTime() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog dialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                timeToNotify = hourOfDay + ":" + minute;
                edt_time.setText(formatTime(hourOfDay,minute));
            }
        }, hour, minute, false);
        dialog.show();
    }


    public String formatTime(int hour, int minute) {

        String time;
        time = "";
        String formattedMinute;

        if (minute / 10 == 0) {
            formattedMinute = "0" + minute;
        } else {
            formattedMinute = "" + minute;
        }


        if (hour == 0) {
            time = "12" + ":" + formattedMinute + " AM";
        } else if (hour < 12) {
            time = hour + ":" + formattedMinute + " AM";
        } else if (hour == 12) {
            time = "12" + ":" + formattedMinute + " PM";
        } else {
            int temp = hour - 12;
            time = temp + ":" + formattedMinute + " PM";
        }


        return time;
    }

    // submit function
    private void addEvent() {
        String event = edt_event.getText().toString();
        String date = edt_date.getText().toString().trim();
        String time = edt_time.getText().toString().trim();

        if (isValid(event, date, time)) {
            DatabaseHelper databaseHelper = new DatabaseHelper(Activity_reminder_create_new.this);
            EventModel eventModel = new EventModel(event, date, time);
            databaseHelper.createEvent(eventModel);

            String dateandtime = date + " " + timeToNotify;
            getRemainingTime(dateandtime);

            finish();
            startActivity(new Intent(Activity_reminder_create_new.this, ActivityReminder_01.class));
        }else {
            Toast.makeText(this, "Pleas fill all fields", Toast.LENGTH_SHORT).show();
        }
    }

    // check all fields are not empty
    private boolean isValid(String event, String date, String time) {
        if (event.isEmpty())
            return false;
        else if (date.isEmpty() || time.isEmpty())
            return false;

        return true;
    }

    // set alarm
    private void setAlarm(String text, String date, String time) {
        AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(getApplicationContext(), AlarmBroadcast.class);
        intent.putExtra("event", text);
        intent.putExtra("time", date);
        intent.putExtra("date", time);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, PendingIntent.FLAG_ONE_SHOT);
        String dateandtime = date + " " + timeToNotify;
        DateFormat formatter = new SimpleDateFormat("d-M-yyyy hh:mm");
        try {
            Date date1 = formatter.parse(dateandtime);
            am.set(AlarmManager.RTC_WAKEUP, date1.getTime(), pendingIntent);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        finish();
    }

    // show remaining time available
    private void getRemainingTime(String then) {
        DateFormat formatter = new SimpleDateFormat("d-M-yyyy hh:mm");
        Date now = new Date();
        int month = now.getMonth() + 1;
        String today = String.valueOf(now.getDate()) + "-" + String.valueOf(now.getMonth() + 1) + "-2021 " + now.getHours() + ":" + now.getMinutes();
        try {

            Date date1 = formatter.parse(then);
            Date date2 = formatter.parse(today);
            long different = date1.getTime() - date2.getTime();

            int numOfDays = (int) (different / (1000 * 60 * 60 * 24));
            int hours = (int) (different / (1000 * 60 * 60));
            int minutes = (int) (different / (1000 * 60));
            int seconds = (int) (different / (1000));

            Toast.makeText(this, "Notify in " + hours + " hours " + minutes + " minutes", Toast.LENGTH_SHORT).show();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}