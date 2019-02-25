package tru.springframework.com.libaryapp.controllers;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tru.springframework.com.libaryapp.model.Author;
import tru.springframework.com.libaryapp.services.AuthorService;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class AuthorControllerTest {

    private AuthorController authorController;

    private MockMvc mockMvc;

    @Mock
    private AuthorService authorService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        authorController = new AuthorController(authorService);
        mockMvc = MockMvcBuilders.standaloneSetup(authorController).build();
    }

    @Test
    public void getAuthorShowPage() throws Exception {
        Author author = new Author();
        author.setId(1l);

        when(authorService.getAuthorById(anyLong())).thenReturn(author);

        mockMvc.perform(get("/author/1/show"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("author"))
                .andExpect(view().name("author/authorShow"));
    }
}