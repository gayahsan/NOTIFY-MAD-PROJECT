package com.example.notifymadproject;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.util.Calendar;

public class NotepadGetNote extends AppCompatActivity {

    EditText nTitle, nDetails;
    NotesDatabase db;
    NotesDataBridge note;
    Calendar calendar;
    String currentDate, currentTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notepad_get_note);

        // Build a notification channel
        NotificationChannel channel;
        channel = new NotificationChannel("Notepad Updates", "Notepad Updates", NotificationManager.IMPORTANCE_DEFAULT);
        NotificationManager manager = getSystemService(NotificationManager.class);
        manager.createNotificationChannel(channel);

        nTitle = findViewById(R.id.txtNoteTitle);
        nDetails = findViewById(R.id.txtNoteField);

        Intent getNote = getIntent();
        long ID = getNote.getLongExtra("ID", 0); // Wrapper type converted to primitive

        db = new NotesDatabase(this);
        note = db.getNote(ID);
        nTitle.setText(note.getTitle());
        nDetails.setText(note.getContent());

        // Get current date and time;
        calendar = Calendar.getInstance();
        currentDate = calendar.get(Calendar.YEAR) + "/" + (calendar.get(Calendar.MONTH) + 1) + "/" + calendar.get(Calendar.DAY_OF_MONTH);
        currentTime = pad(calendar.get(Calendar.HOUR)) + ":" + pad(calendar.get(Calendar.MINUTE));
    }

    private String pad(int i) {
        if (i < 10) {
            return "0" + i;
        } else {
            return String.valueOf(i);
        }
    }

    public void editNoteBtn(View view) {
        // Toast.makeText(this, "Edit note button clicked...", Toast.LENGTH_SHORT).show();
        if (nTitle.getText().length() != 0) {
            note.setTitle(nTitle.getText().toString());
            note.setContent(nDetails.getText().toString());
            int id = db.editNote(note);

            // Building the notification
            String notificationText = "Edited Note ID: " + note.getId() + " as: " + note.getTitle();
            NotificationCompat.Builder builder = new NotificationCompat.Builder(NotepadGetNote.this, "Notepad Updates");
            builder.setContentTitle("Note Updated");
            builder.setContentText(notificationText);
            builder.setSmallIcon(R.drawable.ic_launcher_background);
            builder.setAutoCancel(true);

            // Sending the notification
            NotificationManagerCompat managerCompat = NotificationManagerCompat.from(NotepadGetNote.this);
            managerCompat.notify(1, builder.build());
        } else {
            Toast.makeText(this,"Title cannot be blank...", Toast.LENGTH_SHORT).show();
        }

        goToMain();
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