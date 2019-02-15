package tru.springframework.com.libaryapp.repositories;

import org.springframework.data.repository.CrudRepository;
import tru.springframework.com.libaryapp.model.Author;

public interface AuthorRepository extends CrudRepository<Author,Long> {
}
