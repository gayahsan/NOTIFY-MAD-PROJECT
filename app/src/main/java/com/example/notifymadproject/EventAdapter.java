package com.example.notifymadproject;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notifymadproject.Database.DatabaseHelper;
import com.example.notifymadproject.Database.EventModel;

import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {
    // data members
    List<EventModel> eventModels;
    Context context;
    DatabaseHelper databaseHelper;

    public EventAdapter(List<EventModel> eventModels, Context context) {
        this.eventModels = eventModels;
        this.context = context;
        databaseHelper = new DatabaseHelper(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.reminder_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final EventModel eventModel = eventModels.get(position);
        Log.d("Test", "Id: " + eventModel.getId());
        holder.txt_id.setText(String.valueOf(eventModel.getId()));
        holder.txt_event.setText(eventModel.getEvent());
        holder.txt_date.setText(eventModel.getDate());
        holder.txt_time.setText(eventModel.getTime());
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, ActivityReminderUpdate.class);
                i.putExtra("id", String.valueOf( eventModel.getId()));
                i.putExtra("event", eventModel.getEvent());
                i.putExtra("date", eventModel.getDate());
                i.putExtra("time", eventModel.getTime());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return eventModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txt_id,txt_event, txt_date, txt_time;
        LinearLayout mainLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_id = itemView.findViewById(R.id.txt_id);
            txt_event = itemView.findViewById(R.id.txt_event);
            txt_date = itemView.findViewById(R.id.txt_date);
            txt_time = itemView.findViewById(R.id.txt_time);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
