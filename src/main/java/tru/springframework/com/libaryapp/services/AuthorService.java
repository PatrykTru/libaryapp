package tru.springframework.com.libaryapp.services;

import tru.springframework.com.libaryapp.model.Author;

public interface AuthorService {
   Author getAuthorById(Long id);
}
