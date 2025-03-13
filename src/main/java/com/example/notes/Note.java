package com.example.notes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Note {

    private int id;
    private LocalDate date;
    private String text;


    public Note(int id, LocalDate date, String text) {
        this.id = id;
        this.date = date;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    @Override
    public String toString() {
        return id + ";" + date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) + ";" + text;
    }

}
