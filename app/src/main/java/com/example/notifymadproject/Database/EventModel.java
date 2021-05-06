package com.example.notifymadproject.Database;

public class EventModel {
    Integer id;
    String event;
    String date;
    String time;

    public EventModel(String event, String date, String time) {
        this.event = event;
        this.date = date;
        this.time = time;
    }

    public EventModel(Integer id, String event, String date, String time) {
        this.id = id;
        this.event = event;
        this.date = date;
        this.time = time;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
