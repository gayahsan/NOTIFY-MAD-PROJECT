package com.example.notifymadproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnNotepad;
    Button btnTodo;
    Button btnReminder;
    Button btnProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void intentTodo(View view) {
        Intent intentTodo = new Intent(this, Todo.class);
        startActivity(intentTodo);
    }

    public void intentToNotepad(View view) {
        Intent intenToNotes = new Intent(this, NotepadHome.class);
        startActivity(intenToNotes);
    }
}