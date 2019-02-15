package tru.springframework.com.libaryapp.services;

import tru.springframework.com.libaryapp.model.Book;

import java.util.Set;

public interface BookService {

    Set<Book> getBooks();

    Book findById(Long l);


    void deleteById(Long id);
}
