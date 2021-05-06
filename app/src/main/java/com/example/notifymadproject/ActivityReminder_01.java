package com.example.notifymadproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.notifymadproject.Database.DatabaseHelper;
import com.example.notifymadproject.Database.EventModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ActivityReminder_01 extends AppCompatActivity implements View.OnClickListener {
    // views
    private RecyclerView recyclerView;
    private FloatingActionButton btn_create;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder_01);

        // hooks
        btn_create = findViewById(R.id.btn_create);
        recyclerView = findViewById(R.id.reycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        // event listener
        btn_create.setOnClickListener(this);

        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        List<EventModel> eventModelList = databaseHelper.getAllEvents();

        if (eventModelList.size() > 0) {
            EventAdapter adapter = new EventAdapter(eventModelList, ActivityReminder_01.this);
            recyclerView.setAdapter(adapter);
        }
    }

    @Override
    public void onClick(View v) {
        if (v == btn_create)
            startActivity(new Intent(ActivityReminder_01.this, Activity_reminder_create_new.class));
    }
}