package tru.springframework.com.libaryapp.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@Entity
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String publisherName;

    @OneToMany(cascade = CascadeType.ALL , mappedBy = "author")
    private Set<Book> books = new HashSet<>();
}
