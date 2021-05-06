package com.example.notifymadproject;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {

    LayoutInflater inflater;
    List<NotesDataBridge> notes;

    NotesAdapter(Context context, List<NotesDataBridge> notes) {
        this.inflater = LayoutInflater.from(context);
        this.notes = notes;
    }

    @NonNull
    @Override
    public NotesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Bind
        View view = inflater.inflate(R.layout.custom_list_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesAdapter.ViewHolder holder, int position) {
        // also some bind
        String title, date, time;
        title = notes.get(position).getTitle();
        date = notes.get(position).getDate();
        time = notes.get(position).getTime();

        // I think this is where we passing the data to the viewHolder from database;
        holder.ntitle.setText(title);
        holder.nDate.setText(date);
        holder.nTime.setText(time);
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView ntitle, nDate, nTime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // Bla Bla with the xml file
            ntitle = itemView.findViewById(R.id.nTitle);
            nDate = itemView.findViewById(R.id.nDate);
            nTime = itemView.findViewById(R.id.nTime);

            /*itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Toast.makeText(v.getContext(), "Item Clicked...", Toast.LENGTH_SHORT).show();
                    // i = getNoteIntent
                    // Static viewHolder => normal viewHolder class

                    Intent getNoteIntent = new Intent(v.getContext(), NotepadGetNote.class);
                    getNoteIntent.putExtra("ID", notes.get(getAdapterPosition()).getId());
                    v.getContext().startActivity(getNoteIntent);
                }
            });*/

            itemView.setOnClickListener(v -> { // This one replaced with lambda
                // Toast.makeText(v.getContext(), "Item Clicked...", Toast.LENGTH_SHORT).show();
                // i = getNoteIntent
                // Static viewHolder => normal viewHolder class

                Intent getNoteIntent = new Intent(v.getContext(), NotepadGetNote.class);
                getNoteIntent.putExtra("ID", notes.get(getAdapterPosition()).getId());
                v.getContext().startActivity(getNoteIntent);
            });
        }
    }
}
