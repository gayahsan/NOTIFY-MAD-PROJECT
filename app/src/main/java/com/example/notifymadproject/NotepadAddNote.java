package com.example.notifymadproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class NotepadAddNote extends AppCompatActivity {

    EditText noteTitle, noteDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notepad_add_note);

        noteTitle = findViewById(R.id.txtNoteTitle);
        noteDetails = findViewById(R.id.txtNoteField);
    }
}