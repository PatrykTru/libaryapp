package tru.springframework.com.libaryapp.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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

    @OneToMany(cascade = CascadeType.ALL , mappedBy = "author")
    private final Set<Book> books = new HashSet<>();
}
