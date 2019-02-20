package tru.springframework.com.libaryapp.converters;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import tru.springframework.com.libaryapp.commands.AuthorCommand;
import tru.springframework.com.libaryapp.model.Author;


@Component
public class AuthorToAuthorCommand implements Converter<Author, AuthorCommand> {

    @Nullable
    @Synchronized
    @Override
    public AuthorCommand convert(Author author) {
        if (author ==null)
        return null;

        final AuthorCommand authorCommand = new AuthorCommand();

        authorCommand.setBooks(author.getBooks());
        authorCommand.setFirstName(author.getFirstName());
        authorCommand.setLastName(author.getLastName());
        authorCommand.setId(author.getId());

        return authorCommand;
    }
}
