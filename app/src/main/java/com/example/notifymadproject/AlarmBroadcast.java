package com.example.notifymadproject;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.notifymadproject.Database.DatabaseHelper;
import com.example.notifymadproject.Database.EventModel;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import static com.example.notifymadproject.App.CHANNEL_ID;
public class AlarmBroadcast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        List<EventModel> eventModelList = databaseHelper.getAllEvents();
        Date now = new Date();

        for (EventModel event: eventModelList
             ) {
            String dateTime = event.getDate() + " " + event.getTime();
            DateFormat formatter = new SimpleDateFormat("d-M-yyyy hh:mm");

            String now_date = now.getDate() + "-" + (now.getMonth() + 1) + "-" + "2021";
            String then_date = event.getDate();
            String now_time = formatTime(now.getHours(), now.getMinutes());
            String then_time = event.getTime();

            if (now_date.equals(then_date) && now_time.equals(then_time)){
                notify(context, event.getEvent());
            }
        }
    }

    private void notify(Context context, String text){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_alarm_white_24dp)
                .setContentTitle("Reminder")
                .setContentText(text)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(200, builder.build());
    }

    private String formatTime(int hour, int minute) {
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
}
