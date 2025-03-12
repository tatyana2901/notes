package com.example.notes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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

    @GetMapping("/added")
    public String addNote(String data, String text, Model model) {
        ns.add(data, text);

        return "redirect:/";
    }


}
