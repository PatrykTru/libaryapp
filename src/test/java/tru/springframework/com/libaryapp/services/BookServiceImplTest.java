package tru.springframework.com.libaryapp.services;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tru.springframework.com.libaryapp.commands.BookCommand;
import tru.springframework.com.libaryapp.converters.BookCommandToBook;
import tru.springframework.com.libaryapp.converters.BookToBookCommand;
import tru.springframework.com.libaryapp.model.Book;
import tru.springframework.com.libaryapp.repositories.BookRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;


public class BookServiceImplTest {

    private BookServiceImpl bookService;

    @Mock
    private BookRepository bookRepository;
    @Mock
    private BookToBookCommand bookToBookCommand;
    @Mock
    private BookCommandToBook bookCommandToBook;



    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        bookService = new BookServiceImpl(bookRepository,bookToBookCommand,bookCommandToBook);
    }

    @Test
    public void getBooks() {
        Book book = new Book();
        HashSet<Book> books = new HashSet<>();
        books.add(book);

        when(bookRepository.findAll()).thenReturn(books);

        List<Book> testBookList = bookService.getBooks();

        assertEquals(1, testBookList.size());
        verify(bookRepository ,times(1)).findAll();
        verify(bookRepository,never()).findById(anyLong());

    }

    @Test
    public void findById() {
        Book book = new Book();
        book.setId(1l);
        book.setDescription("This is book description");
        Optional<Book> bookOptional = Optional.of(book);

        when(bookRepository.findById(anyLong())).thenReturn(bookOptional);

        Book testBook = bookService.findById(anyLong());

        assertEquals(new Long(1l) , testBook.getId());
        assertEquals("This is book description" , testBook.getDescription());
        verify(bookRepository,times(1)).findById(anyLong());
        verify(bookRepository,never()).findAll();



    }

    @Test
    public void findByCommandId() {
        Book book = new Book();
        book.setId(1l);
        Optional<Book> bookOptional = Optional.of(book);

        when(bookRepository.findById(anyLong())).thenReturn(bookOptional);

        BookCommand bookCommand = new BookCommand();
        bookCommand.setId(1l);

        when(bookToBookCommand.convert(any())).thenReturn(bookCommand);

        BookCommand commandFromService = bookService.findByCommandId(1l);

        assertNotNull(commandFromService);
        verify(bookRepository,times(1)).findById(anyLong());
        verify(bookRepository,never()).findAll();



    }


    @Test
    public void deleteById() {
        Long id =1l;

        bookService.deleteById(id);

        verify(bookRepository, times(1)).deleteById(anyLong());
    }

    @Test
    public void saveBookCommand() {
       BookCommand bookCommand = new BookCommand();
       bookCommand.setId(1l);
    }

}