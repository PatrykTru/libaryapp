package tru.springframework.com.libaryapp.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tru.springframework.com.libaryapp.model.Author;
import tru.springframework.com.libaryapp.repositories.AuthorRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Override
    public List<Author> getAuthors() {
        List<Author> authors = new ArrayList<>();
        authorRepository.findAll().iterator().forEachRemaining(authors::add);
        List<Author> sortedAuthors = authors.stream().sorted(Comparator.comparing(Author::getId)).collect(Collectors.toList());

        return sortedAuthors;
    }

    @Override
    public Author saveAuthor(Author author) {


        return authorRepository.save(author);
    }
}
