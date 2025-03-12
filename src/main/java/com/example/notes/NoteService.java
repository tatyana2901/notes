package com.example.notes;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@Service
public class NoteService {

    private List<Note> notes = new ArrayList<>();

    public List<Note> getNotes() {
        return notes;
    }

    public NoteService() {
        notes.add(new Note(LocalDate.of(2025, 5, 10), "Вернуть другу журналы и книги."));

    }

    public Note getNoteById(int id) {
        Optional<Note> o = notes.stream().filter(note -> note.getId() == id).findFirst();
        return o.orElse(null);
    }

    public void deleteById(int id) {

        Note n = getNoteById(id);
        if (n != null) notes.remove(n);
    }


    public void add(String date, String text) {

        String[] dates = date.split("-");
        LocalDate ld = LocalDate.of(
                Integer.parseInt(dates[0]),
                Integer.parseInt(dates[1]),
                Integer.parseInt(dates[2]));
        notes.add(new Note(ld, text));
    }
}
