package tru.springframework.com.libaryapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import tru.springframework.com.libaryapp.services.AuthorService;

@Controller
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/author/{id}/show")
    public String getAuthorShowPage(@PathVariable Long id, Model model) {

        model.addAttribute("author", authorService.getAuthorById(id));

        return "author/authorShow";
    }

}
