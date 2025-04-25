package com.example.notes.service;

import com.example.notes.entity.Note;

import java.util.List;

public interface NoteService {
    public List<Note> getAllNotes();

    public void deleteById(int id);

    public void add(Note note);
}