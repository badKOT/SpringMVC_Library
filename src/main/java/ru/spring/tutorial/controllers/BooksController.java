package ru.spring.tutorial.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.spring.tutorial.dao.BookDAO;
import ru.spring.tutorial.dao.PersonDAO;
import ru.spring.tutorial.models.Book;
import ru.spring.tutorial.models.Person;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/books")
public class BooksController {

    private final PersonDAO personDAO;
    private final BookDAO bookDAO;

    @Autowired
    public BooksController(PersonDAO personDAO, BookDAO bookDAO) {
        this.personDAO = personDAO;
        this.bookDAO = bookDAO;
    }

    @GetMapping()
    public String index(Model model, @ModelAttribute("books") Book book) {
        model.addAttribute("books", bookDAO.index());
        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", bookDAO.show(id));
        model.addAttribute("people", personDAO.index());
        model.addAttribute("person", bookDAO.personThatTookTheBook(id));
        return "books/show";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "books/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return "books/new";

        bookDAO.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", bookDAO.show(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "books/edit";

        bookDAO.update(id, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bookDAO.delete(id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/take")
    public String takeBook(@PathVariable("id") int id,
                           @RequestParam("personId") int person_id) {
        bookDAO.takeTheBook(id, person_id);
        return "redirect:/books/" + id;
    }

    @PatchMapping("/{id}/return")
    public String returnBook(@PathVariable("id") int id) {
        bookDAO.returnBook(id);
        return "redirect:/books/" + id;
    }
}
