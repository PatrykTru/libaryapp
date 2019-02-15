package tru.springframework.com.libaryapp.services;

import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tru.springframework.com.libaryapp.model.Book;
import tru.springframework.com.libaryapp.repositories.BookRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class BookServiceImpl implements BookService {

BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Set<Book> getBooks() {

        Set<Book> bookSet = new HashSet<>();
        bookRepository.findAll().iterator().forEachRemaining(bookSet::add);
        return bookSet;
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

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }
}
