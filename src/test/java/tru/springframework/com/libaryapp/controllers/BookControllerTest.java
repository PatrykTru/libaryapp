package tru.springframework.com.libaryapp.controllers;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tru.springframework.com.libaryapp.converters.AuthorToAuthorCommand;
import tru.springframework.com.libaryapp.converters.PublisherToPublisherCommand;
import tru.springframework.com.libaryapp.model.Book;
import tru.springframework.com.libaryapp.services.AuthorService;
import tru.springframework.com.libaryapp.services.BookService;
import tru.springframework.com.libaryapp.services.CategoryService;
import tru.springframework.com.libaryapp.services.PublisherService;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class BookControllerTest {

    private MockMvc mockMvc;

    @Mock
    private BookService bookService;
    @Mock
    private AuthorService authorService;
    @Mock
    private PublisherService publisherService;
    @Mock
    private CategoryService categoryService;

    @Mock
    private AuthorToAuthorCommand authorToAuthorCommand;

    @Mock
    private PublisherToPublisherCommand publisherToPublisherCommand;

    private BookController bookController;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        bookController = new BookController(bookService,authorService,publisherService,categoryService,authorToAuthorCommand,publisherToPublisherCommand);
        mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
    }

    @Test
    public void getBookShow() throws Exception {
        Book book = new Book();
        book.setId(1l);

        when(bookService.findById(book.getId())).thenReturn(book);

        mockMvc.perform(get("/book/1/show"))
                .andExpect(view().name("book/bookShow"))
                .andExpect(model().attributeExists("book"))
                .andExpect(status().isOk());

    }

    @Test
    public void redirectIndexPage() throws Exception
    {
        mockMvc.perform(get("/book/redirect"))
                .andExpect(view().name("redirect:/index"))
                .andExpect(status().is3xxRedirection());
    }
}