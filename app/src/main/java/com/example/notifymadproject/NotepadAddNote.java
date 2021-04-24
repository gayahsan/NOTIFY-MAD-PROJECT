package com.example.notifymadproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class NotepadAddNote extends AppCompatActivity {

    EditText noteTitle, noteDetails;
    Calendar calendar;
    String currentDate, currentTime; // YYYY/MM/DD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notepad_add_note);

        noteTitle = findViewById(R.id.txtNoteTitle);
        noteDetails = findViewById(R.id.txtNoteField);

        // Get current date and time;
        calendar = Calendar.getInstance();
        currentDate = calendar.get(Calendar.YEAR) + "/" + (calendar.get(Calendar.MONTH) + 1) + "/" + calendar.get(Calendar.DAY_OF_MONTH);
        currentTime = pad(calendar.get(Calendar.HOUR)) + ":" + pad(calendar.get(Calendar.MINUTE));

        // Logging
        Log.d("Calendar", "Date and Time: " + currentDate + " and " + currentTime);
    }

    private String pad(int i) {
        if (i < 10) {
            return "0" + i;
        } else {
            return String.valueOf(i);
        }
    }

    public void saveNote(View view) {
        Log.d("saveNote", "In save note...");

        // Save button testing
        Toast saveClicked = Toast.makeText(this, "Save button clicked...", Toast.LENGTH_SHORT);
        saveClicked.show();

        NotesDataBridge note = new NotesDataBridge(noteTitle.getText().toString(), noteDetails.getText().toString(), currentDate, currentTime);
        NotesDatabase noteDB = new NotesDatabase(this);

        long id = noteDB.addNote(note);
    }
}