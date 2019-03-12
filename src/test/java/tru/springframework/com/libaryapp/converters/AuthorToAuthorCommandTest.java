package tru.springframework.com.libaryapp.converters;

import org.junit.Before;
import org.junit.Test;
import tru.springframework.com.libaryapp.commands.AuthorCommand;
import tru.springframework.com.libaryapp.model.Author;
import tru.springframework.com.libaryapp.model.Book;

import static org.junit.Assert.*;

public class AuthorToAuthorCommandTest {
    private final static Long ID_VALUE = 1L;
    private final static String FIRST_NAME = "Adam";
    private final static String LAST_NAME = "Frein";

    private AuthorToAuthorCommand authorToAuthorCommand;

    @Before
    public void setUp() {

        authorToAuthorCommand = new AuthorToAuthorCommand();
    }

    @Test
    public void testNullObject() {
        assertNull(authorToAuthorCommand.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(authorToAuthorCommand.convert(new Author()));
    }
    @Test
    public void convert() {

        Author author = new Author();

        author.setId(ID_VALUE);
        author.setLastName(LAST_NAME);
        author.setFirstName(FIRST_NAME);
        author.getBooks().add(new Book());

        AuthorCommand authorCommand =authorToAuthorCommand.convert(author);

        assertEquals(ID_VALUE,authorCommand.getId());
        assertEquals(LAST_NAME, authorCommand.getLastName());
        assertEquals(FIRST_NAME, authorCommand.getFirstName());



    }
}