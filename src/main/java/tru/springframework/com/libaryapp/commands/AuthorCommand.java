package tru.springframework.com.libaryapp.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tru.springframework.com.libaryapp.model.Book;

import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class AuthorCommand {


    private Long id;

    @Size(min = 3,max = 30)
    private String firstName;
    @Size(min = 3,max = 30)
    private String lastName;
    private Set<Book> books = new HashSet<>();


}
