package tru.springframework.com.libaryapp.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tru.springframework.com.libaryapp.model.Author;
import tru.springframework.com.libaryapp.repositories.AuthorRepository;

import java.util.Optional;

@Service
@Slf4j
public class AuthorServiceImpl implements AuthorService{

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Author getAuthorById(Long id) {

        Optional<Author> authorOptional = authorRepository.findById(id);
        if(!authorOptional.isPresent())
        {
            log.error("wrong id number: " + id);
        }



        return authorOptional.get();
    }
}
