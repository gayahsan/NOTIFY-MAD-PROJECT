package com.example.notifymadproject;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class NotepadGetNote extends AppCompatActivity {

    TextView nTitle, nDetails;
    NotesDatabase db;
    NotesDataBridge note;

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

        db = new NotesDatabase(this);
        note = db.getNote(ID);
        // Toast.makeText(this, "Title: " + note.getTitle(), Toast.LENGTH_SHORT).show();
        nTitle.setText(note.getTitle());
        nDetails.setText(note.getContent());
    }

    public void editNoteBtn(View view) {
        Toast.makeText(this, "Edit note button clicked...", Toast.LENGTH_SHORT).show();
    }

    public void deleteNoteBtn(View view) {
        // Toast.makeText(this, "Delete note button clicked...", Toast.LENGTH_SHORT).show();
        db.deleteNote(note.getId());
        goToMain();
    }

    private void goToMain() {
        Intent intent = new Intent(this, NotepadHome.class);
        startActivity(intent);
    }
}