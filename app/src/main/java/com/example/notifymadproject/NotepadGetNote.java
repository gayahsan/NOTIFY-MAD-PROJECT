package com.example.notifymadproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class NotepadGetNote extends AppCompatActivity {

    TextView nTitle, nDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notepad_get_note);

        // Intent i => getNote;
        // id => ID
        // mDetails => nDetails
        nTitle = findViewById(R.id.txtNoteTitle);
        nDetails = findViewById(R.id.txtNoteField);

        Intent getNote = getIntent();
        // Long ID = getNote.getLongExtra("ID", 0);
        long ID = getNote.getLongExtra("ID", 0); // Wrapper type converted to primitive

        NotesDatabase db = new NotesDatabase(this);
        NotesDataBridge note = db.getNote(ID);
        // Toast.makeText(this, "Title: " + note.getTitle(), Toast.LENGTH_SHORT).show();
        nTitle.setText(note.getTitle());
        nDetails.setText(note.getContent());
    }
}