package tru.springframework.com.libaryapp.services;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tru.springframework.com.libaryapp.model.Author;
import tru.springframework.com.libaryapp.repositories.AuthorRepository;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class AuthorServiceImplTest {

    AuthorServiceImpl authorService;
    @Mock
    AuthorRepository authorRepository;
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        authorService = new AuthorServiceImpl(authorRepository);
    }

    @Test
    public void getAuthorById() {
        Author author = new Author();
        author.setId(1l);
        Optional<Author> authorOptional = Optional.of(author);

        when(authorRepository.findById(anyLong())).thenReturn(authorOptional);

        Author testAuthor = authorRepository.findById(anyLong()).get();

        assertEquals(author.getId(), testAuthor.getId());
        verify(authorRepository,times(1)).findById(anyLong());
        verify(authorRepository,never()).findAllById(anyIterable());

    }
}