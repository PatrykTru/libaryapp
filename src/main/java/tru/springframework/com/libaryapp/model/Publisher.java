package tru.springframework.com.libaryapp.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Publisher {

    @Builder
    public Publisher(String publisherName) {
        this.publisherName = publisherName;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String publisherName;

    @OneToMany(cascade = CascadeType.ALL , mappedBy = "publisher")
    private final List<Book> books = new ArrayList<>();

    public List<Book> getBooks() {
        List<Book> sortedBooks = books.stream().sorted(Comparator.comparing(Book::getId)).collect(Collectors.toList());

        return sortedBooks;
    }
}
