package tru.springframework.com.libaryapp.model;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Book {

    @Builder
    public Book(String bookName, Author author, Publisher publisher, String description, int numberOfPages, LocalDate publishDate, CoverType coverType, BigInteger eanNumber, Price price, String imageName) {
        this.bookName = bookName;
        this.author = author;
        this.publisher = publisher;
        this.description = description;
        this.numberOfPages = numberOfPages;
        this.publishDate = publishDate;
        this.coverType = coverType;
        this.eanNumber = eanNumber;
        this.price = price;
        this.imageName = imageName;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bookName;

    @OneToOne(cascade = CascadeType.DETACH)
    private Author author;
    @OneToOne(cascade = CascadeType.DETACH)
    private Publisher publisher;
    @Lob
    private String description;
    private Integer numberOfPages;
    private LocalDate publishDate;
    @Enumerated(value = EnumType.STRING)
    private CoverType coverType;
    private BigInteger eanNumber;
    @OneToOne(cascade = CascadeType.ALL)
    private Price price;
    @Lob
    private Byte[] image;
    private String imageName;

    private ArrayList categoryList = new ArrayList();

    @ManyToMany
    @JoinTable(name = "book_category" ,joinColumns = @JoinColumn(name = "book_id"),
    inverseJoinColumns = @JoinColumn(name = "category_id"))
    private final Set<Category> categories =  new HashSet<>();



}
