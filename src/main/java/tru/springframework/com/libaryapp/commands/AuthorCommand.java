package tru.springframework.com.libaryapp.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tru.springframework.com.libaryapp.model.Book;

import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class AuthorCommand {


    private Long id;

    @Size(min = 3,max = 30)
    private String firstName;
    @Size(min = 3,max = 30)
    private String lastName;
    private List<Book> books = new ArrayList<>();

    public List<Book> getBooks() {
        List<Book> sortedBooks = books.stream().sorted(Comparator.comparing(Book::getId)).collect(Collectors.toList());

        return sortedBooks;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
