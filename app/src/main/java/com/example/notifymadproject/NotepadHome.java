package com.example.notifymadproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

public class NotepadHome extends AppCompatActivity {

    RecyclerView recyclerView;
    Button fab;
    NotesAdapter adapter;
    List<NotesDataBridge> notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notepad_home);

        // Getting the notes from database
        NotesDatabase db = new NotesDatabase(this);
        notes = db.getNotes();

        // Linking the recycler view
        recyclerView = findViewById(R.id.listOfNotes);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new NotesAdapter(this, notes);
        recyclerView.setAdapter(adapter);
    }

    public void intentToEdit(View view) {
        Intent intentToEdit  = new Intent(this, NotepadAddNote.class);
        startActivity(intentToEdit);
    }
}