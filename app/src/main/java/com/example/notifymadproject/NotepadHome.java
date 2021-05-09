package com.example.notifymadproject;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
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
    int notesCountInt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notepad_home);

        // Build a notification channel
        NotificationChannel channel;
        channel = new NotificationChannel("Test Notification", "Test Notification", NotificationManager.IMPORTANCE_DEFAULT);
        NotificationManager manager = getSystemService(NotificationManager.class);
        manager.createNotificationChannel(channel);

        // Getting the notes from database
        NotesDatabase db = new NotesDatabase(this);
        notes = db.getNotes();

        // Getting notes count from the database
        notesCount = "Notes count: " + db.getNoteCount();
        notesCountInt = db.getNoteCount();

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

    protected int checkFreeStorageFull(int notesCountInt, double percentage) {
        if (notesCountInt == 10 && percentage == 100.0) {
            return 1;
        } else {
            return 0;
        }
    }

    public void intentToEdit(View view) { // Add note
        if (percentage >= 100.00) {
            Toast.makeText(this, "Buy full version to add more notes...", Toast.LENGTH_SHORT).show();
        } else {
            Intent intentToEdit  = new Intent(this, NotepadAddNote.class);
            startActivity(intentToEdit);
        }
    }

    public void testNotification(View view) {
        // Building the notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(NotepadHome.this, "Test Notification");
        builder.setContentTitle("Test Notification");
        builder.setContentText("Test notification body");
        builder.setSmallIcon(R.drawable.ic_launcher_background);
        builder.setAutoCancel(true);

        // Sending the notification
        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(NotepadHome.this);
        managerCompat.notify(1, builder.build());
    }
}