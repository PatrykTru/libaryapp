package tru.springframework.com.libaryapp.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import tru.springframework.com.libaryapp.model.CoverType;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
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
    private AuthorCommand author;
    private PublisherCommand publisher;

    @Size(min=3 , max= 255)
    private String description;

    @Min(30)
    @Max(4500)
    private int numberOfPages;

    @DateTimeFormat
    private LocalDate publishDate;
    private CoverType coverType;
    private BigInteger eanNumber;

    private PriceCommand price;
    @NotEmpty
    private Byte[] image;
    private Set<CategoryCommand> categories =  new HashSet<>();



}
