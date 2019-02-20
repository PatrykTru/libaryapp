package tru.springframework.com.libaryapp.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tru.springframework.com.libaryapp.model.*;

import javax.validation.constraints.*;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class BookCommand {

    private Long id;
    @Size(min=3 , max= 30)
    private String bookName;
    private AuthorCommand authorCommand;
    private PublisherCommand publisherCommand;

    @Size(min=3 , max= 255)
    private String description;

    @Min(30)
    @Max(4500)
    private int numberOfPages;

    private LocalDate publishDate;
    private CoverType coverType;
    private BigInteger eanNumber;

    private PriceCommand priceCommand;
    @NotEmpty
    private Byte[] image;
    private Set<CategoryCommand> categories =  new HashSet<>();



}
