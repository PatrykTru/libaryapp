package tru.springframework.com.libaryapp.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import tru.springframework.com.libaryapp.commands.AuthorCommand;
import tru.springframework.com.libaryapp.services.AuthorService;
import tru.springframework.com.libaryapp.services.BookService;

import javax.validation.Valid;

@Controller
@Slf4j
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

    @GetMapping("author/createAuthor")
    public String getAuthorCreationPage(Model model)
    {
        model.addAttribute("author", new AuthorCommand());


        return "author/newOrEdit";
    }

    @GetMapping("/author/{id}/delete")
    public String deleteAuthor(@PathVariable String id){

        authorService.deleteById(Long.valueOf(id));
        return "redirect:/";
    }
    @PostMapping("/author")
    public String createOrUpdateAuthorHandling(@Valid @ModelAttribute("author") AuthorCommand authorCommand
            , BindingResult bindingResult) {

        AuthorCommand savedCommand = authorService.saveAuthorCommand(authorCommand);

        System.out.println(savedCommand.getId());
        System.out.println(authorCommand.getId());
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(objectError
                    -> log.debug(objectError.toString()));

            authorService.deleteById(savedCommand.getId());
            return "author/newOrEdit";

        }
        return "redirect:/author/" + savedCommand.getId() + "/show";
    }

    @GetMapping("/author/redirect")
    public String redirectIndexPage() {

        return "redirect:/index";
    }

}
