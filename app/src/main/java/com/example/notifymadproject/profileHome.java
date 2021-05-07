package com.example.notifymadproject;

import android.app.LoaderManager;
import android.content.ContentUris;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

//import android.support.v7.app.AppCompatActivity;

public class profileHome extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    public ProfileAdapter mAdapter;
    public static final  int CONTACTLOADER = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_home);
        //Toast.makeText(this, "On Profile Home...", Toast.LENGTH_SHORT).show();


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(v -> {
            Intent intent = new Intent(profileHome.this, profileEdit.class);
            startActivity(intent);
        });

        ListView listView = findViewById(R.id.list);
        mAdapter = new ProfileAdapter(this, null);
        listView.setAdapter(mAdapter);

        // whenever we press a listview for updating

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(profileHome.this, profileEdit.class);
            Uri newUri = ContentUris.withAppendedId(ProfileContract.ContactEntry.CONTENT_URI, id);
            intent.setData(newUri);
            startActivity(intent);

        });

        // get the loader running
        getLoaderManager().initLoader(CONTACTLOADER, null, this);
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args)  {

        String[] projection = {ProfileContract.ContactEntry._ID,
                ProfileContract.ContactEntry.COLUMN_NAME,
                ProfileContract.ContactEntry.COLUMN_EMAIL,
                ProfileContract.ContactEntry.COLUMN_PICTURE,
                ProfileContract.ContactEntry.COLUMN_PHONENUMBER,
                ProfileContract.ContactEntry.COLUMN_TYPEOFCONTACT
        };

        return new CursorLoader(this, ProfileContract.ContactEntry.CONTENT_URI,
                projection, null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.swapCursor(null);
    }
}