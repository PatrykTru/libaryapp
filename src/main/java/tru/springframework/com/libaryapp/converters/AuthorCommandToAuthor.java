package tru.springframework.com.libaryapp.converters;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import tru.springframework.com.libaryapp.commands.AuthorCommand;
import tru.springframework.com.libaryapp.model.Author;

import javax.validation.constraints.Null;

@Component
public class AuthorCommandToAuthor implements Converter<AuthorCommand, Author> {

    @Nullable
    @Synchronized
    @Override
    public Author convert(AuthorCommand authorCommand) {
        if(authorCommand==null)
        return null;

        final Author author = new Author();

        author.setId(authorCommand.getId());
        author.setFirstName(authorCommand.getFirstName());
        author.setLastName(authorCommand.getLastName());
        author.getBooks().addAll(authorCommand.getBooks());

        return author;
    }
}
