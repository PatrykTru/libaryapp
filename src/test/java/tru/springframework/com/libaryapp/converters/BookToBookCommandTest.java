package tru.springframework.com.libaryapp.converters;

import org.junit.Before;
import org.junit.Test;
import tru.springframework.com.libaryapp.commands.BookCommand;
import tru.springframework.com.libaryapp.model.*;

import java.math.BigInteger;
import java.time.LocalDate;

import static org.junit.Assert.*;

public class BookToBookCommandTest {
    private static final Long ID_VALUE = 1l;
    private static final String BOOK_NAME = "book name";
    private static final String DESCRIPTION = "This book is about nothing";
    private static final Integer NUMBER_OF_PAGES = 400;
    private static final LocalDate PUBLISH_DATE = LocalDate.of(1994, 12, 12);
    private static final CoverType COVER_TYPE = CoverType.SOFT;
    private static final BigInteger EAN_NUMBER = new BigInteger("123123123123");
    private final static Long CAT_ID1 = 2l;
    private final static Long CAT_ID2 = 3l;
    private final static Long PRICE_ID = 4l;
    private final static Long PUBLISHER_ID = 5l;
    private final static Long AUTHOR_ID = 8l;

    BookToBookCommand bookToBookCommand;

    @Before
    public void setUp() {
        bookToBookCommand = new BookToBookCommand(new AuthorToAuthorCommand(), new CategoryToCategoryCommand(),
                new PriceToPriceCommand(), new PublisherToPublisherCommand());
    }

    @Test
    public void testNullObject() {
        assertNull(bookToBookCommand.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(bookToBookCommand.convert(new Book()));
    }

    @Test
    public void convert() {
        Book book = new Book();

        book.setPublishDate(PUBLISH_DATE);
        book.setNumberOfPages(NUMBER_OF_PAGES);
        book.setId(ID_VALUE);
        book.setEanNumber(EAN_NUMBER);
        book.setDescription(DESCRIPTION);
        book.setCoverType(COVER_TYPE);
        book.setBookName(BOOK_NAME);


        Category category1 = new Category();
        category1.setId(CAT_ID1);
        Category category2 = new Category();
        category2.setId(CAT_ID2);
        book.getCategories().add(category1);
        book.getCategories().add(category2);

        Price price = new Price();
        price.setId(PRICE_ID);
        book.setPrice(price);

        Publisher publisher = new Publisher();
        publisher.setId(PUBLISHER_ID);
        book.setPublisher(publisher);

        Author author = new Author();
        author.setId(AUTHOR_ID);
        book.setAuthor(author);

        BookCommand bookCommand = bookToBookCommand.convert(book);


        assertNotNull(bookCommand);
        assertEquals(PUBLISH_DATE, bookCommand.getPublishDate());
        assertEquals(NUMBER_OF_PAGES, bookCommand.getNumberOfPages());
        assertEquals(ID_VALUE, bookCommand.getId());
        assertEquals(BOOK_NAME, bookCommand.getBookName());
        assertEquals(DESCRIPTION, bookCommand.getDescription());
        assertEquals(COVER_TYPE, bookCommand.getCoverType());
        assertEquals(2, bookCommand.getCategories().size());
        assertEquals(PUBLISHER_ID, bookCommand.getPublisher().getId());
        assertEquals(AUTHOR_ID, bookCommand.getAuthor().getId());
        assertEquals(PRICE_ID, bookCommand.getPrice().getId());

    }

}