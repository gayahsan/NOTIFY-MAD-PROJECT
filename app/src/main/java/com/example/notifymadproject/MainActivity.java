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