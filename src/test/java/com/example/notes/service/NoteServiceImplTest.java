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
import static org.mockito.Mockito.*;

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

        Mockito.doReturn(Arrays.asList(note1, note2, note3, note4)).when(noteRepository).findAll(); //задаем результат работы метода findAll
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
        //arrange
        Note noteToDelete = new Note(LocalDate.now(), "noteToDelete");
        noteToDelete.setId(5);
        //act
        noteService.deleteById(5);
        //assert
        verify(noteRepository).deleteById(noteToDelete.getId());
    }

    @Test
    void add() {
        //arrange
        Note noteToSave = new Note(LocalDate.now(), "savedNote");
        //act
        noteService.add(noteToSave);
        //assert
        verify(noteRepository).save(noteToSave); //проверка, что при вызове метода add() был вызван метод save()
    }

    @Test
    void getNoteById_ShouldReturnNote_WhenNoteExists() {
        //arrange - подготавливаем данные для тестирования
        Note note = new Note(LocalDate.now(), "test_note");
        note.setId(1);
        Mockito.doReturn(Optional.of(note)).when(noteRepository).findById(1);
        //act - выполняем тестируемый метод
        Note result = noteService.getNoteById(1);
        //assert - проверяем, совпадает ли фактический результат с ожидаемым
        assertEquals(note, result);
        verify(noteRepository, times(1)).findById(1); //проверка что при вызове метода getById() был вызван метод findById()
    }

    @Test
    void updateNote_whenIdExists() {
        //arrange
        Note original = new Note(LocalDate.of(2025, 4, 2), "original");
        original.setId(2);
        Note updatedResult = new Note(LocalDate.of(2025, 9, 7), "updated"); //то, что передается в реальный метод update в виде параметра
        updatedResult.setId(2);
        when(noteRepository.findById(updatedResult.getId())).thenReturn(Optional.of(original));//задаем правило, по которому при вызое метода findId возвращается original note
        //act
        noteService.updateNote(updatedResult);
        //assert
        verify(noteRepository).save(original);
        assertEquals("updated", original.getContent()); //проверяем, что первоначальные данные originalNote поменялись на новые
        assertEquals(LocalDate.of(2025, 9, 7), original.getDate());

    }
    @Test
    void updateNote_whenIdNotExist() {
        //arrange
        Note original = null;
        Note updatedResult = new Note(LocalDate.of(2025, 9, 7), "updated"); //то, что передается в реальный метод update в виде параметра
        updatedResult.setId(2);
        //assert
        assertThrows(NullPointerException.class,()->noteService.updateNote(updatedResult));
    }
}