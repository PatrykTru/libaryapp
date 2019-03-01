package tru.springframework.com.libaryapp.repositories;

import org.springframework.data.repository.CrudRepository;
import tru.springframework.com.libaryapp.model.Book;


public interface BookRepository extends CrudRepository<Book,Long> {
}
