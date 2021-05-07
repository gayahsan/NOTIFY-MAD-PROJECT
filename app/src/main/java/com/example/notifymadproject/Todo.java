package com.example.notifymadproject;


import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notifymadproject.Adapters.ToDoAdapter;
import com.example.notifymadproject.Model.ToDoModel;
import com.example.notifymadproject.Utils.DatabaseHandler;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Collections;
import java.util.List;

public class Todo extends AppCompatActivity implements DialogCloseListener{

    private DatabaseHandler db;

    private RecyclerView tasksRecyclerView;
    private ToDoAdapter tasksAdapter;
    private FloatingActionButton fab;

    private List<ToDoModel> taskList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_todo);

            // Build a notification channel
            NotificationChannel channel;
            channel = new NotificationChannel("TODOUpdates", "TODOUpdates", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);

            db = new DatabaseHandler(this);
            db.openDatabase();

            tasksRecyclerView = findViewById(R.id.tasksRecyclerView);
            tasksRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            tasksAdapter = new ToDoAdapter(db,Todo.this);
            tasksRecyclerView.setAdapter(tasksAdapter);

            ItemTouchHelper itemTouchHelper = new
                    ItemTouchHelper(new RecyclerItemTouchHelper(tasksAdapter));
            itemTouchHelper.attachToRecyclerView(tasksRecyclerView);

            fab = findViewById(R.id.fab);

            taskList = db.getAllTasks();
            Collections.reverse(taskList);

            tasksAdapter.setTasks(taskList);

            fab.setOnClickListener(v -> AddNewTask.newInstance().show(getSupportFragmentManager(), AddNewTask.TAG));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handleDialogClose(DialogInterface dialog){
        taskList = db.getAllTasks();
        Collections.reverse(taskList);
        tasksAdapter.setTasks(taskList);
        tasksAdapter.notifyDataSetChanged();
    }

    /*public static void completeNotification(View view) {
        // Building the notification
        String notificationText = "Test";
        String notificationTitle = "Test";
        NotificationCompat.Builder builder = new NotificationCompat.Builder(Todo.this, "TODOUpdates");
        builder.setContentTitle(notificationTitle);
        builder.setContentText(notificationText);
        builder.setSmallIcon(R.drawable.ic_launcher_background);
        builder.setAutoCancel(true);
    }*/
}