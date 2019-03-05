package tru.springframework.com.libaryapp.services;

import tru.springframework.com.libaryapp.model.Author;

import java.util.List;

public interface AuthorService {
   Author getAuthorById(Long id);
   List<Author> getAuthors();

}
