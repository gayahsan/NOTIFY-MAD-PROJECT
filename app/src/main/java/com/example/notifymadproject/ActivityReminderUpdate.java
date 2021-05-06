package com.example.notifymadproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.notifymadproject.Database.DatabaseHelper;
import com.example.notifymadproject.Database.EventModel;

import java.util.Calendar;

public class ActivityReminderUpdate extends AppCompatActivity implements View.OnClickListener{
    // views
    private EditText edt_event, edt_date, edt_time;
    private ImageView ic_calendar, ic_time, btn_update, btn_delete;

    private String id, event, date, time;
    private String timeToNotify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder_update);

        // hooks
        edt_event = findViewById(R.id.edt_up_event);
        edt_date = findViewById(R.id.edt_up_date);
        edt_time = findViewById(R.id.edt_up_time);
        ic_calendar = findViewById(R.id.ic_up_calendar);
        ic_time = findViewById(R.id.ic_up_time);
        btn_update = findViewById(R.id.btn_update);
        btn_delete = findViewById(R.id.btn_delete);

        getData();

        // event listener
        ic_calendar.setOnClickListener(this);
        ic_time.setOnClickListener(this);
        btn_update.setOnClickListener(this);
        btn_delete.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == ic_calendar)
            selectDate();
        else if (v == ic_time)
            selectTime();
        else if (v == btn_update)
            updateEvent();
        else if (v == btn_delete)
            deleteEvent();

    }

    // get intent data
    private void getData() {
        if (getIntent().hasExtra("id")){
            id = getIntent().getStringExtra("id");
            event = getIntent().getStringExtra("event");
            date = getIntent().getStringExtra("date");
            time = getIntent().getStringExtra("time");

            edt_event.setText(event);
            edt_date.setText(date);
            edt_time.setText(time);
        }
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
                edt_date.setText(dayOfMonth + "-" + month + "-" + year);
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

    // update an event
    private void updateEvent() {
        String event = edt_event.getText().toString();
        String date = edt_date.getText().toString().trim();
        String time = edt_time.getText().toString().trim();

        if (isValid(event, date, time)) {
            DatabaseHelper databaseHelper = new DatabaseHelper(ActivityReminderUpdate.this);
            EventModel eventModel = new EventModel(Integer.parseInt(id),event, date, time);
            databaseHelper.updateData(eventModel);
            finish();
            Toast.makeText(this, "Reminder updated!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(ActivityReminderUpdate.this, ActivityReminder_01.class));
        }else {
            Toast.makeText(this, "Pleas fill all fields", Toast.LENGTH_SHORT).show();
        }
    }

    // check validity of form
    private boolean isValid(String event, String date, String time) {
        if (event.isEmpty())
            return false;
        else if (date.isEmpty() || time.isEmpty())
            return false;

        return true;
    }

    // delete an event
    private void deleteEvent() {
        DatabaseHelper databaseHelper = new DatabaseHelper(ActivityReminderUpdate.this);
        databaseHelper.deleteData(id);
        finish();
        Toast.makeText(this, "Reminder removed!", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(ActivityReminderUpdate.this, ActivityReminder_01.class));
    }
}