package tru.springframework.com.libaryapp.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tru.springframework.com.libaryapp.model.Book;

import javax.persistence.CascadeType;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class PublisherCommand {


    private Long id;

    private String publisherName;
    private Set<Book> books = new HashSet<>();
}
