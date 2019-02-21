package tru.springframework.com.libaryapp.converters;

import org.junit.Before;
import org.junit.Test;
import tru.springframework.com.libaryapp.commands.AuthorCommand;
import tru.springframework.com.libaryapp.commands.BookCommand;
import tru.springframework.com.libaryapp.model.Author;
import tru.springframework.com.libaryapp.model.Book;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class AuthorCommandToAuthorTest {

    private final static Long ID_VALUE = 1L;
    private final static String FIRST_NAME = "Adam";
    private final static String LAST_NAME = "Frein";



    private AuthorCommandToAuthor authorCommandToAuthor;


    @Before
    public void setUp() {

        authorCommandToAuthor = new AuthorCommandToAuthor();
    }

    @Test
    public void testNullObject() {
        assertNull(authorCommandToAuthor.convert(null));
}

    @Test
    public void testEmptyObject() {
        assertNotNull(authorCommandToAuthor.convert(new AuthorCommand()));
    }
    @Test
    public void convert() {

        AuthorCommand authorCommand = new AuthorCommand();

        authorCommand.setId(ID_VALUE);
        authorCommand.setLastName(LAST_NAME);
        authorCommand.setFirstName(FIRST_NAME);
        authorCommand.getBooks().add(new Book());

        Author author =authorCommandToAuthor.convert(authorCommand);

        assertEquals(ID_VALUE,author.getId());
        assertEquals(LAST_NAME, author.getLastName());
        assertEquals(FIRST_NAME, author.getFirstName());
        assertEquals(1, author.getBooks().size());


    }
}