package com.example.notes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class NoteController {

    @Autowired
    NoteService ns;

    @GetMapping("/")
    public String goToFirstView(Model model) {
        List<Note> list = ns.getNotes();
        model.addAttribute("tab_lines", list);
        return "first-view";
    }

    @GetMapping("/deleteNote")
    public String delete(int id) {
        ns.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/editNote/{id}")
    public String edit(@PathVariable int id, Model model) {

        Note note = ns.getNoteById(id);
        model.addAttribute("note", note);
        return "edit-view";
    }

    @PostMapping("/update_note")
    public String updateNote(@ModelAttribute Note note) {
        ns.editNote(note);
        return "redirect:/";
    }

    @GetMapping("/added")
    public String addNote(int id, String data, String text, Model model) {

        if (ns.isIdUnique(id)) {
            ns.add(id, data, text);
        } else model.addAttribute("err", "Заметка с таким id уже существует!"); ;

        List<Note> list = ns.getNotes();
        model.addAttribute("tab_lines", list);

        return "first-view";
    }


}
