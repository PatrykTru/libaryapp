package tru.springframework.com.libaryapp.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Author author;
    @OneToOne
    private Publisher publisher;
    private String description;
    private int numberOfPages;
    private LocalDate publishDate;
    private CoverType coverType;
    private Long eanNumber;
    @OneToOne
    private Price price;
    @Lob
    private Byte[] image;

    @ManyToMany
    @JoinTable(name = "book_category" ,joinColumns = @JoinColumn(name = "book_id"),
    inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories =  new HashSet<>();



}
