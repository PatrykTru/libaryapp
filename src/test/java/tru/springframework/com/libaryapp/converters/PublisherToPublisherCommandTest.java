package tru.springframework.com.libaryapp.converters;

import org.junit.Before;
import org.junit.Test;
import tru.springframework.com.libaryapp.commands.PublisherCommand;
import tru.springframework.com.libaryapp.model.Book;
import tru.springframework.com.libaryapp.model.Publisher;

import static org.junit.Assert.*;

public class PublisherToPublisherCommandTest {

    private final static Long ID_VALUE = 1L;
    private final static String NAME = "Publisher";
    private final static Long BOOK_ID = 2L;

    PublisherToPublisherCommand publisherToPublisherCommand;

    @Before
    public void setUp() {
        publisherToPublisherCommand = new PublisherToPublisherCommand();
    }

    @Test
    public void testNullObject() {
        assertNull(publisherToPublisherCommand.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(publisherToPublisherCommand.convert(new Publisher()));
    }

    @Test
    public void convert() {
        Publisher publisher = new Publisher();
        publisher.setPublisherName(NAME);
        publisher.setId(ID_VALUE);
        Book book = new Book();
        book.setId(BOOK_ID);
        publisher.getBooks().add(book);

        PublisherCommand publisherCommand = publisherToPublisherCommand.convert(publisher);

        assertEquals(NAME, publisherCommand.getPublisherName());
        assertEquals(ID_VALUE, publisherCommand.getId());
        assertEquals(1, publisherCommand.getBooks().size());
    }
}