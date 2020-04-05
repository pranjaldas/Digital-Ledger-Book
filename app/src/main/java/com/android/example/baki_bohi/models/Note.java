package com.android.example.baki_bohi.models;

public class Note {
    private String note_id;
    private String sid;
    private String subject;
    private String description;
    private String date;
    private String time;

    public Note() {
    }

    public Note(String note_id, String sid, String subject, String description, String date, String time) {
        this.note_id = note_id;
        this.sid = sid;
        this.subject = subject;
        this.description = description;
        this.date = date;
        this.time = time;
    }

    public String getNote_id() {
        return note_id;
    }

    public void setNote_id(String note_id) {
        this.note_id = note_id;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    @Override
    public String toString() {
        return "Note{" +
                "note_id='" + note_id + '\'' +
                ", sid='" + sid + '\'' +
                ", subject='" + subject + '\'' +
                ", description='" + description + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
