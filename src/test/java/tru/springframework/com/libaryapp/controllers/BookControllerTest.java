package tru.springframework.com.libaryapp.controllers;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tru.springframework.com.libaryapp.model.Book;
import tru.springframework.com.libaryapp.services.BookService;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class BookControllerTest {

    private MockMvc mockMvc;

    @Mock
    private BookService bookService;

    private BookController bookController;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        bookController = new BookController(bookService);
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
}