package com.example.notes.service;

import com.example.notes.entity.Note;
import com.example.notes.repository.NoteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class NoteServiceImplTest {

    @Mock
    private NoteRepository noteRepository;

    @InjectMocks
    private NoteServiceImpl noteService;

    @Test
    void getAllNotes_ShouldReturnSortedListOfNotes() {
        //arrange - подготовливаем и настраиваем данные
        Note note1 = new Note(LocalDate.of(2025, 5, 10), "Note_1");
        note1.setId(2);
        Note note2 = new Note(LocalDate.of(2025, 5, 4), "Note_2");
        note2.setId(4);
        Note note3 = new Note(LocalDate.of(2025, 5, 3), "Note_3");
        note3.setId(3);
        Note note4 = new Note(LocalDate.of(2025, 5, 4), "Note_4");
        note4.setId(1);

        Mockito.doReturn(Arrays.asList(note3, note4, note2, note1)).when(noteRepository).findAll(); //задаем результат работы метода findAll

        //act - выполняем метод после предварительной подготовки данных
        List<Note> result = noteService.getAllNotes();

        //assert - проверяем, как работает
        assertEquals(4, result.size());
        assertEquals(LocalDate.of(2025, 5, 3), result.get(0).getDate());
        assertEquals(LocalDate.of(2025, 5, 4), result.get(2).getDate());
        assertEquals(LocalDate.of(2025, 5, 4), result.get(1).getDate());
        assertEquals(LocalDate.of(2025, 5, 10), result.get(3).getDate());
        assertEquals(3, result.get(0).getId());
        assertEquals(1, result.get(1).getId());
        assertEquals(4, result.get(2).getId());
        assertEquals(2, result.get(3).getId());

        verify(noteRepository, times(1)).findAll();//проверка ,что запустился нужный метод 1 раз
    }

    @Test
    void deleteById() {
    }

    @Test
    void add() {
    }

    @Test
    void getNoteById_ShouldReturnNote_WhenNoteExists() {
        //arrange - подготавливае данные для тестирования
        Note note = new Note(LocalDate.now(), "test_note");
        note.setId(1);
        Mockito.doReturn(Optional.of(note)).when(noteRepository).findById(1);
        //act - выполняем тестируемый метод
        Note result = noteService.getNoteById(1);

        //assert - проверяем, совпадает ли фактический результат с ожидаемым
        assertEquals(note, result);
        verify(noteRepository, times(1)).findById(1);
    }

    @Test
    void updateNote() {
    }
}