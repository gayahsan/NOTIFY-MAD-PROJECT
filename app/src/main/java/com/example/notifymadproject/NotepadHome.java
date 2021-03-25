package com.example.notifymadproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NotepadHome extends AppCompatActivity {

    RecyclerView recycler;
    Button fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notepad_home);

        /*fab = findViewById(R.id.fab_add_note);*/
        /*recycler = findViewById(R.id.rec_notes_view);*/
    }

    public void intentToEdit(View view) {
        Intent intentToEdit  = new Intent(this, NotepadAddNote.class);
        startActivity(intentToEdit);
    }
}