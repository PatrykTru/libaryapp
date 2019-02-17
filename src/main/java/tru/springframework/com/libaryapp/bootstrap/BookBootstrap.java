package tru.springframework.com.libaryapp.bootstrap;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import tru.springframework.com.libaryapp.model.*;
import tru.springframework.com.libaryapp.repositories.AuthorRepository;
import tru.springframework.com.libaryapp.repositories.BookRepository;
import tru.springframework.com.libaryapp.repositories.CategoryRepository;
import tru.springframework.com.libaryapp.repositories.PublisherRepository;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class BookBootstrap  implements ApplicationListener<ContextRefreshedEvent> {

   private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private CategoryRepository categoryRepository;
    private PublisherRepository publisherRepository;

    public BookBootstrap(AuthorRepository authorRepository, BookRepository bookRepository,
                         CategoryRepository categoryRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.categoryRepository = categoryRepository;
        this.publisherRepository = publisherRepository;
    }

    @Transactional
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        bookRepository.saveAll(getBooks());
        log.debug("Im Bootstrap onAppEvent");
    }

    private List<Book> getBooks(){
        List<Book> books = new ArrayList<>();

        Publisher publisher1 = Publisher.builder().publisherName("Fabryka Słów").id(1l).build();
        Publisher publisher2 = Publisher.builder().publisherName("New Booksite").id(2l).build();
        Publisher publisher3 = Publisher.builder().publisherName("Leader Paper").id(3l).build();

        Category categoryFantasy = Category.builder().description("Fantasy").id(1l).build();
        Category categoryRomance = Category.builder().description("Romance").id(2l).build();
        Category categoryBiography = Category.builder().description("Biography").id(3l).build();
        Category categoryHistory = Category.builder().description("Biography").id(3l).build();
        Category categoryCriminal = Category.builder().description("Biography").id(3l).build();

        Author author1 = Author.builder().firstName("Jacek").lastName("Piekara").id(1l).build();
        Author author2 = Author.builder().firstName("Sylvia").lastName("Day").id(2l).build();
        Author author3 = Author.builder().firstName("Donato").lastName("Carrisi").id(3l).build();
        Author author4 = Author.builder().firstName("Michelle").lastName("Obama").id(4l).build();
        Author author5 = Author.builder().firstName("Jakub").lastName("Staszak").id(5l).build();


        Book book1 = Book.builder().author(author1).coverType(CoverType.SOFT).description("" +
                "\n" +
                "\n" +
                "Arnold Lowefell i Mordimer Madderdin wspólnie ruszają do walki!\n" +
                "\n" +
                "Dwaj szczególni Inkwizytorzy znowu się spotykają. Tym razem, by wspólnie wypełnić zadanie w przeklętej krainie zamieszkanej przez dziki lud, który wiarę w Chrystusa połączył z pogańskimi obrządkami. Tysiące mil od granic Cesarstwa, inkwizytorzy będą musieli spełnić misję kluczową dla całego chrześcijaństwa.\n" +
                "\n" +
                "W całej intrydze wielką rolę odegrają także mało znani lecz potężni funkcjonariusze Wewnętrznego Kręgu Inkwizytorium. Hildegarda Reizend - podróżniczka, która przewędrowała świat od Afryki po Himalaje. Biegły w sztuce magicznej Barnaba Biber. Wszyscy będą musieli stawić czoła Herenniuszowi Furiusowi - Rzymianinowi, który na własne oczy widział Ukrzyżowanie i Zstąpienie Jezusa Chrystusa.\n" +
                "\n" +
                "Węzły intryg zaciskają się na szyjach wszystkich bohaterów. Kto będzie musiał umrzeć dla dobra chrześcijaństwa, a kto przeżyje, by dalej służyć Sprawie?\n" +
                "")
                .eanNumber(new BigInteger("9788379643950")).id(1l)
                .price(Price.builder().currency(Currency.ZŁ).value(31.92).build())
                .publishDate(LocalDate.of(2019,2,27))
                .publisher(publisher1)
                .numberOfPages(412)
                .bookName("Płomień i krzyż – Tom 3")
                .build();

        book1.getCategories().add(categoryFantasy);


        Book book2 = Book.builder().author(author2).coverType(CoverType.HARD).description("" +
                "\n" +
                "Szczegóły\n" +
                "Mroczna strona zmysłowości. Jak wiele można zaryzykować w imię pożądliwej, szaleńczej miłości? Czy trudna przeszłość Evy i Gideona stanie na przeszkodzie do ich szczęścia?\n" +
                "\n" +
                "Gideon Cross. Na samo wspomnienie jego imienia moje ciało ogarniały fale pożądania i tęsknoty. Od chwili gdy go zobaczyłam, gdy po raz pierwszy zajrzałam poza oszałamiającą, nieziemsko piękną powierzchowność i dostrzegłam kryjącego się pod nią mrocznego, niebezpiecznego mężczyznę, poczułam nieodparte przyciąganie, jakbym odnalazła drugą połowę samej siebie. Potrzebowałam go jak bicia własnego serca, a on wystawił się na ogromne niebezpieczeństwo, zaryzykował dla mnie wszystko. " +
                "")
                .eanNumber(new BigInteger("9788381390149")).id(2l)
                .price(Price.builder().currency(Currency.ZŁ).value(40.00).build())
                .publishDate(LocalDate.of(2019,1,30))
                .publisher(publisher2)
                .numberOfPages(415)
                .bookName("Wyznanie Crossa")
                .build();

        book2.getCategories().add(categoryRomance);

        Book book3 = Book.builder().author(author3).coverType(CoverType.SOFT).description("" +
                "Gdy zapadają ciemności, pojawia się strach...\n" +
                "\n" +
                "\n" +
                "Gwałtowna burza doprowadza do awarii elektrowni i zmusza władze Rzymu do ogłoszenia dwudziestoczterogodzinnej przerwy w dostawie prądu. A już przed pięciuset laty papież Leon X w tajemniczej bulli ostrzegał, aby chronić Rzym przed pogrążeniem się w ciemnościach...\n" +
                "\n" +
                "\n" +
                "Nad miasto, w którym zapanowały panika i chaos, nadciąga w milczeniu mroczny cień pozostawiający za sobą szlak pełen trupów i... znaków. Może je odczytać tylko Marcus, łowca cieni przeszkolony w rozpoznawaniu anomalii występujących na miejscach zbrodni. Penitencjariusz utracił jednak swoją najcenniejszą broń – pamięć – co daje mordercy olbrzymią przewagę.")
                .eanNumber(new BigInteger("9788381254663")).id(3l)
                .price(Price.builder().currency(Currency.ZŁ).value(35.90).build())
                .publishDate(LocalDate.of(2019,1,30))
                .publisher(publisher3)
                .numberOfPages(336)
                .bookName("Władca ciemności")
                .build();

        book3.getCategories().add(categoryCriminal);

        Book book4 = Book.builder().author(author4).coverType(CoverType.HARD).description("" +
               "Osobiste wspomnienia najbardziej lubianej Pierwszej Damy Stanów Zjednoczonych\n" +
                "\n" +
                "\n" +
                "\n" +
                "Michelle Obama opisuje doświadczenia, które ją ukształtowały – od dzieciństwa w południowym Chicago, przez lata pracy na kierowniczym stanowisku, kiedy godziła macierzyństwo z karierą, aż do czasu spędzonego w najsłynniejszym domu świata. Pisząc z niebywałą szczerością, odwagą i humorem, odkrywa kulisy swojego życia rodzinnego. Opisuje, jak Obamowie znaleźli się w centrum zainteresowania światowych mediów i jak wyglądało ich życie w Białym Domu przez osiem kluczowych lat, kiedy poznawała Amerykę, zaś Ameryka poznawała ją. To zaskakująco intymny rozrachunek życia kobiety wrażliwej i stanowczej, która konsekwentnie odmawiała spełniania oczekiwań innych i której historia zachęca, by pójść w jej ślady. ")
                .eanNumber(new BigInteger("9788326826306")).id(4l)
                .price(Price.builder().currency(Currency.ZŁ).value(32.30).build())
                .publishDate(LocalDate.of(2019,2,13))
                .publisher(publisher2)
                .numberOfPages(500)
                .bookName("Becoming. Moja historia")
                .build();

        book4.getCategories().add(categoryBiography);

        Book book5 = Book.builder().author(author5).coverType(CoverType.SOFT).description("" +
               "Czyn zbrojny powstania wielkopolskiego nie ma szczęścia w trafianiu do zbiorowej świadomości Polaków. O ile legionowa maciejówka czy błękitny mundur armii Hallera są dość powszechnie znane, to przybrana biało-czerwoną rozetką feldmyca czy późniejsza wielkopolska rogatywka wciąż muszą przebijać swą drogę do narodowej pamięci. Podobnie jest z powstańczymi bitwami. Trwające ponad półtora miesiąca walki, w których uczestniczyła artyleria i pociągi pancerne, ciągle nie są powszechnie znane, a nierzadko niewiele wiedzą o nich nawet mieszkańcy miejscowości, o które toczył się bój.\n" +
                "\n" +
                "\n" +
                "\n" +
                "Problem ten w znacznym stopniu został zauważony już w okresie międzywojennym. Powstałe wówczas Towarzystwo do Badania Historii Powstania Wielkopolskiego rozpoczęło działalność wydawniczą zmierzającą do zmiany tego stanu rzeczy. Jego staraniem została wydane m.in. obszerna monografia drugiej bitwy o Szubin ze stycznia 1919r.")
                .eanNumber(new BigInteger(" 9788311155985")).id(5l)
                .price(Price.builder().currency(Currency.ZŁ).value(29.90).build())
                .publishDate(LocalDate.of(2019,1,17))
                .publisher(publisher1)
                .numberOfPages(500)
                .bookName("Rawicz 1919 ")
                .build();

        book5.getCategories().add(categoryHistory);

        books.add(book1);
        books.add(book2);
        books.add(book3);
        books.add(book4);
        books.add(book5);

        return books;
    }
}

