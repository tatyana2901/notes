package com.example.notes.controller;
import com.example.notes.entity.Note;
import com.example.notes.service.NoteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class NoteController {

    @Autowired
    NoteService noteService;

    @GetMapping("/")
    public String goToFirstView(Model model) {
        List<Note> list = noteService.getAllNotes();
        model.addAttribute("tab_lines", list);
        model.addAttribute("note", new Note());
        return "first-view";
    }

    @GetMapping("/deleteNote")
    public String delete(int id) {
        noteService.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/editNote/{id}")
    public String edit(@PathVariable int id, Model model) {
        Note note = noteService.getNoteById(id);
        model.addAttribute("note", note);
        return "edit-view";
    }

    @PostMapping("/update_note")
    public String updateNote(@ModelAttribute Note note) {
        noteService.updateNote(note);
        return "redirect:/";
    }

    @GetMapping("/added")
    public String addNote(@Valid @ModelAttribute Note note, BindingResult bindingResult, Model model) {//важен порядок - bindingresult сразу после параметра валидации

        if (bindingResult.hasErrors()) {
            List<Note> list = noteService.getAllNotes();
            model.addAttribute("tab_lines", list);
            return "first-view";
        }
        noteService.add(note);
        return "redirect:/";
    }


}
