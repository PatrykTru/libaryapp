package tru.springframework.com.libaryapp.converters;

import org.junit.Before;
import org.junit.Test;
import tru.springframework.com.libaryapp.commands.BookCommand;
import tru.springframework.com.libaryapp.commands.PublisherCommand;
import tru.springframework.com.libaryapp.model.Book;
import tru.springframework.com.libaryapp.model.Publisher;

import static org.junit.Assert.*;

public class PublisherCommandToPublisherTest {

    private final static Long ID_VALUE = 1L;
    private final static String NAME = "Publisher";
    private final static Long BOOK_ID = 2L;


    PublisherCommandToPublisher publisherCommandToPublisher;
    @Before
    public void setUp() {
        publisherCommandToPublisher = new PublisherCommandToPublisher();
    }

    @Test
    public void testNullObject() {
        assertNull(publisherCommandToPublisher.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(publisherCommandToPublisher.convert(new PublisherCommand()));
    }
    @Test
    public void convert() {
        PublisherCommand publisherCommand = new PublisherCommand();
        publisherCommand.setPublisherName(NAME);
        publisherCommand.setId(ID_VALUE);
        Book book = new Book();
        book.setId(BOOK_ID);
        publisherCommand.getBooks().add(book);

        Publisher publisher  = publisherCommandToPublisher.convert(publisherCommand);

        assertEquals(ID_VALUE, publisher.getId());
        assertEquals(1, publisher.getBooks().size());
        assertEquals(NAME, publisher.getPublisherName());

    }
}