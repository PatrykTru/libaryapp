package tru.springframework.com.libaryapp.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tru.springframework.com.libaryapp.commands.BookCommand;
import tru.springframework.com.libaryapp.converters.BookCommandToBook;
import tru.springframework.com.libaryapp.converters.BookToBookCommand;
import tru.springframework.com.libaryapp.model.Book;
import tru.springframework.com.libaryapp.repositories.BookRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class BookServiceImpl implements BookService {

        private final BookRepository bookRepository;
        private final BookToBookCommand bookToBookCommand;
        private final BookCommandToBook bookCommandToBook;

    public BookServiceImpl(BookRepository bookRepository, BookToBookCommand bookToBookCommand, BookCommandToBook bookCommandToBook) {
        this.bookRepository = bookRepository;
        this.bookToBookCommand = bookToBookCommand;
        this.bookCommandToBook = bookCommandToBook;
    }



    @Override
    public List<Book> getBooks() {

        List<Book> bookSet = new ArrayList<>();
        bookRepository.findAll().iterator().forEachRemaining(bookSet::add);
        List<Book> sortedBooks = bookSet.stream().sorted(Comparator.comparing(Book::getId)).collect(Collectors.toList());
        return sortedBooks;
    }


    @Override
    public Book findById(Long l) {
        Optional<Book> bookOptional = bookRepository.findById(l);
        if(!bookOptional.isPresent())
        {
            log.debug("wrong id" + l.toString());
        }

        return bookOptional.get();
    }

    @Transactional
    @Override
    public BookCommand findByCommandId(Long l) {

        return bookToBookCommand.convert(findById(l));
    }

    @Transactional
    @Override
    public BookCommand saveBookCommand(BookCommand command) {


        Book book = bookCommandToBook.convert(command);
        Book savedBook = bookRepository.save(book);
        log.error("id  " + savedBook.getId() );
        log.debug("Saved Book id:" +savedBook.getId());
        return bookToBookCommand.convert(savedBook);
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }
}
