package com.example.notes.service;
import com.example.notes.entity.Note;
import com.example.notes.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    private NoteRepository noteRepository;

    public NoteServiceImpl() {
    }

    @Override
    public List<Note> getAllNotes() {
        List<Note> notes = noteRepository.findAll();
        //сортировка полученного списка по датам заметок, а внутри равных дат по id
        notes.sort(Comparator.comparing(Note::getDate).thenComparing(Note::getId));
        return notes;
    }

    @Override
    public void deleteById(int id) {
        noteRepository.deleteById(id);
    }

    @Override
    public void add(Note note) {
        noteRepository.save(note);
    }

    @Override
    public Note getNoteById(int id) {
        return noteRepository.findById(id).orElse(null);
    }

    @Override
    public void updateNote(Note note) {
        Note updNote = noteRepository.findById(note.getId()).orElse(null);
        updNote.setDate(note.getDate()); //исключение с null перехватывается ExceptionHandler
        updNote.setContent(note.getContent());
        noteRepository.save(updNote);
    }


/*

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
