package com.example.notes.exceptions;
import com.example.notes.entity.Note;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//обработка ошибок nullPointerException
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NullPointerException.class)
    public String handleNullPointerException(Exception ex, Model model) {
        model.addAttribute("err", "Объект не найден.");
        model.addAttribute("note",new Note());
        return "edit-view";
    }
//обработка всех остальных возможных ошибок
    @ExceptionHandler(Exception.class)
    public String handleAnyException(Exception ex, Model model) {
        model.addAttribute("err", "Объект не найден.");
        model.addAttribute("note",new Note());
        return "first-view";
    }
}
