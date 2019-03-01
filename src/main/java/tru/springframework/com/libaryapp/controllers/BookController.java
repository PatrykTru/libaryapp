package tru.springframework.com.libaryapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import tru.springframework.com.libaryapp.services.BookService;

@Controller
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
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


}
