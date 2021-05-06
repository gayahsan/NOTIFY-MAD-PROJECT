package com.example.notifymadproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NotepadHome extends AppCompatActivity {

    RecyclerView recyclerView;
    Button fab;
    NotesAdapter adapter;
    List<NotesDataBridge> notes;
    String notesCount, notesPercentage;
    double percentage;
    TextView notesCountView, notesPercentageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notepad_home);

        // Getting the notes from database
        NotesDatabase db = new NotesDatabase(this);
        notes = db.getNotes();

        // Getting notes count from the database
        notesCount = "Notes count: " + db.getNoteCount();

        // Linking with XML
        recyclerView = findViewById(R.id.listOfNotes);
        notesCountView = findViewById(R.id.txtNoteCount);
        notesPercentageView = findViewById(R.id.txtStorageCount);

        percentage = ((db.getNoteCount()) / 10.00) * 100.00;
        notesPercentage = "Free storage used: " + percentage + "%";

        // Sending to XML
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new NotesAdapter(this, notes);
        recyclerView.setAdapter(adapter);
        notesCountView.setText(notesCount);
        notesPercentageView.setText(notesPercentage);
    }

    public void intentToEdit(View view) { // Add note
        if (percentage >= 100.00) {
            Toast.makeText(this, "Buy full version to add more notes...", Toast.LENGTH_SHORT).show();
        } else {
            Intent intentToEdit  = new Intent(this, NotepadAddNote.class);
            startActivity(intentToEdit);
        }
    }
}