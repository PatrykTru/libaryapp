package tru.springframework.com.libaryapp.converters;

import org.junit.Before;
import org.junit.Test;
import tru.springframework.com.libaryapp.commands.AuthorCommand;
import tru.springframework.com.libaryapp.commands.BookCommand;
import tru.springframework.com.libaryapp.commands.PriceCommand;
import tru.springframework.com.libaryapp.commands.PublisherCommand;
import tru.springframework.com.libaryapp.model.Book;
import tru.springframework.com.libaryapp.model.Category;
import tru.springframework.com.libaryapp.model.CoverType;

import java.math.BigInteger;
import java.time.LocalDate;

import static org.junit.Assert.*;

public class BookCommandToBookTest {
    private static final Long ID_VALUE = 1l;
    private static final String BOOK_NAME = "book name";
    private static final String DESCRIPTION = "This book is about nothing";
    private static final Integer NUMBER_OF_PAGES = 400;
    private static final LocalDate PUBLISH_DATE = LocalDate.of(1994,12,12);
    private static final  CoverType COVER_TYPE = CoverType.SOFT;
    private static final  BigInteger EAN_NUMBER = new BigInteger("123123123123");
    private final static Long CAT_ID1 = 2l;
    private final static Long CAT_ID2 = 3l;
    private final static Long PRICE_ID = 4l;
    private final static Long PUBLISHER_ID = 5l;
    private final static Long AUTHOR_ID = 8l;

    BookCommandToBook bookCommandToBook;

    @Before
    public void setUp() {
        bookCommandToBook = new BookCommandToBook(new CategoryCommandToCategory(),new PublisherCommandToPublisher(),
                            new PriceCommandToPrice(), new AuthorCommandToAuthor());
    }

    @Test
    public void testNullObject() {
        assertNull(bookCommandToBook.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(bookCommandToBook.convert(new BookCommand()));
    }

    @Test
    public void convert() {
        BookCommand bookCommand = new BookCommand();

        bookCommand.setPublishDate(PUBLISH_DATE);
        bookCommand.setNumberOfPages(NUMBER_OF_PAGES);
        bookCommand.setId(ID_VALUE);
        bookCommand.setEanNumber(EAN_NUMBER);
        bookCommand.setDescription(DESCRIPTION);
        bookCommand.setCoverType(COVER_TYPE);
        bookCommand.setBookName(BOOK_NAME);


        Category categoryCommand1 = new Category();
        categoryCommand1.setId(CAT_ID1);
        Category categoryCommand2 = new Category();
        categoryCommand2.setId(CAT_ID2);
        bookCommand.getCategories().add(categoryCommand1);
        bookCommand.getCategories().add(categoryCommand2);

        PriceCommand priceCommand = new PriceCommand();
        priceCommand.setId(PRICE_ID);
        bookCommand.setPrice(priceCommand);

        PublisherCommand publisherCommand = new PublisherCommand();
        publisherCommand.setId(PUBLISHER_ID);
        bookCommand.setPublisher(publisherCommand);

        AuthorCommand authorCommand = new AuthorCommand();
        authorCommand.setId(AUTHOR_ID);
        bookCommand.setAuthor(authorCommand);

        Book book = bookCommandToBook.convert(bookCommand);


        assertNotNull(book);
        assertEquals(PUBLISH_DATE , book.getPublishDate());
        assertEquals(NUMBER_OF_PAGES , book.getNumberOfPages());
        assertEquals(ID_VALUE , book.getId());
        assertEquals(BOOK_NAME , book.getBookName());
        assertEquals(DESCRIPTION , book.getDescription());
        assertEquals(COVER_TYPE , book.getCoverType());
        assertEquals(2 , book.getCategories().size());
        assertEquals(PUBLISHER_ID , book.getPublisher().getId());
        assertEquals(AUTHOR_ID , book.getAuthor().getId());
        assertEquals(PRICE_ID , book.getPrice().getId());

    }
}