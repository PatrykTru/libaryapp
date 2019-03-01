package tru.springframework.com.libaryapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import tru.springframework.com.libaryapp.services.AuthorService;
import tru.springframework.com.libaryapp.services.BookService;

@Controller
public class AuthorController {

    private final AuthorService authorService;
    private final BookService bookService;


    public AuthorController(AuthorService authorService, BookService bookService) {
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @GetMapping("/author/{id}/show")
    public String getAuthorShowPage(@PathVariable Long id, Model model) {

        model.addAttribute("author", authorService.getAuthorById(id));

        return "author/authorShow";
    }

    @GetMapping("/author/redirect")
    public String redirectIndexPage() {

        return "redirect:/index";
    }

}
