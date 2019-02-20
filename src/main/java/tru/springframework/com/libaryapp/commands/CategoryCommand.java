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
public class CategoryCommand {

    private Long id;
    @Size(min = 3,max = 255)
    private String description;
    private  final Set<Book> books = new HashSet<>();

}
