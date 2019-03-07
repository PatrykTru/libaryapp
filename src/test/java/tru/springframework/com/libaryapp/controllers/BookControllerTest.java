package tru.springframework.com.libaryapp.controllers;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tru.springframework.com.libaryapp.commands.BookCommand;
import tru.springframework.com.libaryapp.converters.AuthorToAuthorCommand;
import tru.springframework.com.libaryapp.converters.PublisherToPublisherCommand;
import tru.springframework.com.libaryapp.model.Book;
import tru.springframework.com.libaryapp.services.*;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
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
    ImageService imageService;
    @Mock
    private AuthorToAuthorCommand authorToAuthorCommand;

    @Mock
    private PublisherToPublisherCommand publisherToPublisherCommand;

    private BookController bookController;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        bookController = new BookController(bookService, authorService, publisherService, categoryService, imageService, authorToAuthorCommand, publisherToPublisherCommand);
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
    public void redirectIndexPage() throws Exception {
        mockMvc.perform(get("/book/redirect"))
                .andExpect(view().name("redirect:/index"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void createBook() throws Exception {

        mockMvc.perform(get("/book/createBook"))
                .andExpect(model().attributeExists("book"))
                .andExpect(model().attributeExists("authors"))
                .andExpect(model().attributeExists("publishers"))
                .andExpect(model().attributeExists("categories"))
                .andExpect(view().name("book/new"))
                .andExpect(status().isOk());
    }

    @Test
    public void updateBook() throws Exception {

        BookCommand bookCommand = new BookCommand();
        bookCommand.setId(10l);

        when(bookService.findByCommandId(anyLong())).thenReturn(bookCommand);


        mockMvc.perform(get("/book/1/update"))
                .andExpect(view().name("book/new"))
                .andExpect(status().isOk());

    }

    @Test
    public void deleteBook() {
        BookCommand bookCommand = new BookCommand();
        bookCommand.setId(10l);

        bookService.deleteById(10l);

        verify(bookService, times(1)).deleteById(10l);

    }

}