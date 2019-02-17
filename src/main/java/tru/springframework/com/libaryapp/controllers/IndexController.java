package tru.springframework.com.libaryapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tru.springframework.com.libaryapp.services.BookService;


@Controller
public class IndexController {

       private BookService bookService;

    public IndexController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping({"" ,"/" , "index"})
    public String getIndexPage(Model model){
    model.addAttribute("books", bookService.getBooks());

        return "index";
    }
}
