package tru.springframework.com.libaryapp.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import tru.springframework.com.libaryapp.commands.AuthorCommand;
import tru.springframework.com.libaryapp.commands.BookCommand;
import tru.springframework.com.libaryapp.commands.PublisherCommand;
import tru.springframework.com.libaryapp.converters.AuthorToAuthorCommand;
import tru.springframework.com.libaryapp.converters.PublisherToPublisherCommand;
import tru.springframework.com.libaryapp.model.Author;
import tru.springframework.com.libaryapp.model.Category;
import tru.springframework.com.libaryapp.model.Publisher;
import tru.springframework.com.libaryapp.services.AuthorService;
import tru.springframework.com.libaryapp.services.BookService;
import tru.springframework.com.libaryapp.services.CategoryService;
import tru.springframework.com.libaryapp.services.PublisherService;

import javax.validation.Valid;
import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Controller
@Slf4j
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;
    private final PublisherService publisherService;
    private final CategoryService categoryService;

    private final AuthorToAuthorCommand authorToAuthorCommand;
    private final PublisherToPublisherCommand publisherToPublisherCommand;

    public BookController(BookService bookService, AuthorService authorService, PublisherService publisherService, CategoryService categoryService, AuthorToAuthorCommand authorToAuthorCommand, PublisherToPublisherCommand publisherToPublisherCommand) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.publisherService = publisherService;
        this.categoryService = categoryService;
        this.authorToAuthorCommand = authorToAuthorCommand;
        this.publisherToPublisherCommand = publisherToPublisherCommand;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder binder)
    {

        binder.registerCustomEditor(LocalDate.class, new PropertyEditorSupport() {

            @Override
            public void setAsText(java.lang.String text) throws IllegalArgumentException {
                setValue(LocalDate.parse(text));
            }

        });
    }
    @GetMapping("/book/{bookId}/show")
    public java.lang.String getBookShow(@PathVariable Long bookId, Model model) {
        model.addAttribute("book", bookService.findById(bookId));

        return "book/bookShow";
    }

    @GetMapping("/book/redirect")
    public java.lang.String redirectIndexPage() {

        return "redirect:/index";
    }

    @GetMapping("/book/createBook")
    public java.lang.String createBook(Model model) {

       /*        List<Category> categoriesList = categoryService.getCategories();
        Category[] categories = categoriesList.toArray(new Category[categoriesList.size()]);
*/
        model.addAttribute("book" , new BookCommand());
        model.addAttribute("authors", authorService.getAuthors() );
        model.addAttribute("publishers" ,publisherService.getPublishers());
        model.addAttribute("categories" ,categoryService.getCategories());

        return "book/new";
    }


    @PostMapping("book")
    public java.lang.String saveOrUpdate(@Valid @ModelAttribute(name = "book" ) BookCommand bookCommand,
                                         BindingResult bindingResult) {

        Author author = authorService.getAuthorById(bookCommand.getAuthor().getId());
        AuthorCommand authorCommand = authorToAuthorCommand.convert(author);
        Publisher publisher = publisherService.getPublisherById(bookCommand.getPublisher().getId());
        PublisherCommand publisherCommand = publisherToPublisherCommand.convert(publisher);
        Set<Category> tmpCategories = new HashSet<>();
        for (int i = 0; i < bookCommand.getCategoryList().size(); i++)
            tmpCategories.add(categoryService.findById(Long.valueOf(
                    String.valueOf(bookCommand.getCategoryList().get(i)))));

            bookCommand.setCategories(tmpCategories);
            bookCommand.setPublisher(publisherCommand);
            bookCommand.setAuthor(authorCommand);

            BookCommand savedCommand = bookService.saveBookCommand(bookCommand);

            if (bindingResult.hasErrors()) {
                bindingResult.getAllErrors().forEach(objectError
                        -> log.debug(objectError.toString()));
                return "book/new";
            }
            return "redirect:/book/" + savedCommand.getId() + "/show";
        }



}
