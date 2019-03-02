package tru.springframework.com.libaryapp.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import tru.springframework.com.libaryapp.commands.BookCommand;
import tru.springframework.com.libaryapp.services.AuthorService;
import tru.springframework.com.libaryapp.services.BookService;
import tru.springframework.com.libaryapp.services.CategoryService;
import tru.springframework.com.libaryapp.services.PublisherService;

import javax.validation.Valid;
import java.beans.PropertyEditorSupport;
import java.time.LocalDate;

@Controller
@Slf4j
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;
    private final PublisherService publisherService;
    private final CategoryService categoryService;

    public BookController(BookService bookService, AuthorService authorService, PublisherService publisherService, CategoryService categoryService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.publisherService = publisherService;
        this.categoryService = categoryService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder binder)
    {
        binder.setDisallowedFields("id","book.author.id");

        binder.registerCustomEditor(LocalDate.class, new PropertyEditorSupport() {

            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                setValue(LocalDate.parse(text));
            }

        });
    }
    @GetMapping("/book/{bookId}/show")
    public String getBookShow(@PathVariable Long bookId, Model model) {
        model.addAttribute("book", bookService.findById(bookId));

        return "book/bookShow";
    }

    @GetMapping("/book/redirect")
    public String redirectIndexPage() {

        return "redirect:/index";
    }

    @GetMapping("/book/createBook")
    public String createBook(Model model) {

        model.addAttribute("book" , new BookCommand());
        model.addAttribute("authors", authorService.getAuthors() );
        model.addAttribute("publishers" ,publisherService.getPublishers());
        model.addAttribute("categories" ,categoryService.getCategories());

        return "book/new";
    }

    @PostMapping("book")
    public String saveOrUpdate(@Valid @ModelAttribute("book") BookCommand bookCommand, BindingResult bindingResult){
        BookCommand savedCommand = bookService.saveBookCommand(bookCommand);

        if(bindingResult.hasErrors())
        {
            bindingResult.getAllErrors().forEach(objectError
                    -> log.debug(objectError.toString()));

            return "book/new";
        }


        return "redirect:/book/"+ savedCommand.getId() +"/bookShow" ;

    }


}
