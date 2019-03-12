package tru.springframework.com.libaryapp.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import tru.springframework.com.libaryapp.commands.AuthorCommand;
import tru.springframework.com.libaryapp.commands.BookCommand;
import tru.springframework.com.libaryapp.commands.PublisherCommand;
import tru.springframework.com.libaryapp.converters.AuthorToAuthorCommand;
import tru.springframework.com.libaryapp.converters.BookCommandToBook;
import tru.springframework.com.libaryapp.converters.BookToBookCommand;
import tru.springframework.com.libaryapp.converters.PublisherToPublisherCommand;
import tru.springframework.com.libaryapp.model.Book;
import tru.springframework.com.libaryapp.repositories.BookRepository;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookServiceIT {

    public static final String BOOK_DESCRIPTION = "new Description";
    public static final String BOOK_NAME = "new BookName";

    @Autowired
    private BookService bookService;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookToBookCommand bookToBookCommand;
    @Autowired
    private BookCommandToBook bookCommandToBook;

    @Autowired
    private AuthorToAuthorCommand authorToAuthorCommand;

    @Autowired
    private PublisherToPublisherCommand publisherToPublisherCommand;




    @Transactional
    @Test
    public void TestOfBookSaving()
    {
        Iterable<Book> books = bookRepository.findAll();
        Book testBook = books.iterator().next();
        BookCommand bookCommand = bookToBookCommand.convert(testBook);

        AuthorCommand testAuthor = authorToAuthorCommand.convert(testBook.getAuthor());
        PublisherCommand testPublisher = publisherToPublisherCommand.convert(testBook.getPublisher());

        bookCommand.setBookName(BOOK_NAME);
        bookCommand.setDescription(BOOK_DESCRIPTION);
        BookCommand savedCommand = bookService.saveBookCommand(bookCommand);


        assertEquals(BOOK_NAME, savedCommand.getBookName());
        assertEquals(BOOK_DESCRIPTION,savedCommand.getDescription());
        assertEquals(testBook.getCategories().size() , savedCommand.getCategories().size());
        assertEquals(testAuthor.getId(),savedCommand.getAuthor().getId());
        assertEquals(testPublisher.getId(),savedCommand.getPublisher().getId());


    }

}
