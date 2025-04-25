package com.example.notes.service;

import com.example.notes.entity.Note;
import com.example.notes.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    private NoteRepository noteRepository;


    public NoteServiceImpl() {

    }

    @Override
    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    @Override
    public void deleteById(int id) {
        noteRepository.deleteById(id);

    }

    @Override
    public void add(Note note) {
        noteRepository.save(note);
    }



   /* public Note getNoteById(int id) {
        Optional<Note> o = notes.stream().filter(note -> note.getId() == id).findFirst();
        return o.orElse(null);
    }

    public void deleteById(int id) {

        Note n = getNoteById(id);
        if (n != null) notes.remove(n);
    }

    public boolean isIdUnique(int id) {
        Optional<Note> opt = notes.stream().filter(note -> note.getId() == id).findFirst();
        return opt.isEmpty();
    }

    public void editNote(Note note) {
        Note updNote = getNoteById(note.getId());
        updNote.setDate(note.getDate());
        updNote.setText(note.getText());
    }


    public void add(int id, String date, String text) {

        String[] dates = date.split("-");
        LocalDate ld = LocalDate.of(
                Integer.parseInt(dates[0]),
                Integer.parseInt(dates[1]),
                Integer.parseInt(dates[2]));
        notes.add(new Note(id, ld, text));
    }


  //  @Scheduled(fixedDelay = 30000)
    public void saveNotesToFile() {

        String fileName = "savedNotes.txt";
        List<String> savedList = notes.stream().map(Note::toString).toList();

        try (FileWriter fw = new FileWriter(fileName)) {
            Files.write(Paths.get(fileName), savedList, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


    }
*/

}
