package tru.springframework.com.libaryapp.bootstrap;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import tru.springframework.com.libaryapp.model.*;
import tru.springframework.com.libaryapp.repositories.*;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class BookBootstrap  implements ApplicationListener<ContextRefreshedEvent> {

    private final static String currency = "Zł";

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;
    private final PublisherRepository publisherRepository;
    private final PriceRepository priceRepository;

    public BookBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, CategoryRepository categoryRepository,
                         PublisherRepository publisherRepository, PriceRepository priceRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.categoryRepository = categoryRepository;
        this.publisherRepository = publisherRepository;
        this.priceRepository = priceRepository;
    }

    @Transactional
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        bookRepository.saveAll(getLibaryBooks());
        log.debug("Im Bootstrap onAppEvent");
    }

    private List<Book> getLibaryBooks(){
        List<Book> books = new ArrayList<>();

        Publisher publisher1 = Publisher.builder().publisherName("Fabryka Słów").build();
        Publisher publisher2 = Publisher.builder().publisherName("New Booksite").build();
        Publisher publisher3 = Publisher.builder().publisherName("Leader Paper").build();

        publisher1.setId(1l);
        publisher2.setId(2l);
        publisher3.setId(3l);

        publisherRepository.save(publisher1);
        publisherRepository.save(publisher2);
        publisherRepository.save(publisher3);


        Category categoryFantasy = Category.builder().description("Fantasy").build();
        Category categoryRomance = Category.builder().description("Romance").build();
        Category categoryBiography = Category.builder().description("Biography").build();
        Category categoryHistory = Category.builder().description("History").build();
        Category categoryCriminal = Category.builder().description("Criminal").build();

        categoryFantasy.setId(1l);
        categoryRomance.setId(2l);
        categoryBiography.setId(3l);
        categoryHistory.setId(4l);
        categoryCriminal.setId(5l);

        categoryRepository.save(categoryFantasy);
        categoryRepository.save(categoryRomance);
        categoryRepository.save(categoryBiography);
        categoryRepository.save(categoryHistory);
        categoryRepository.save(categoryCriminal);

        Author author1 = Author.builder().firstName("Jacek").lastName("Piekara").build();
        Author author2 = Author.builder().firstName("Sylvia").lastName("Day").build();
        Author author3 = Author.builder().firstName("Donato").lastName("Carrisi").build();
        Author author4 = Author.builder().firstName("Michelle").lastName("Obama").build();
        Author author5 = Author.builder().firstName("Jakub").lastName("Staszak").build();

        author1.setId(1l);
        author2.setId(2l);
        author3.setId(3l);
        author4.setId(4l);
        author5.setId(5l);

        authorRepository.save(author1);
        authorRepository.save(author2);
        authorRepository.save(author3);
        authorRepository.save(author4);
        authorRepository.save(author5);

        Price price1 = Price.builder().currency(currency).value(31.92).build();
        priceRepository.save(price1);

        Book book1 = Book.builder().author(author1).coverType(CoverType.SOFT).imageName("piekaraImage.jpg").description("" +
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
                .eanNumber(new BigInteger("9788379643950"))
                .price(price1)
                .publishDate(LocalDate.of(2019,2,27))
                .publisher(publisher1)
                .numberOfPages(412)
                .bookName("Płomień i krzyż – Tom 3")
                .build();

        book1.setId(1l);
        categoryFantasy.getBooks().add(book1);
        author1.getBooks().add(book1);
        book1.getCategories().add(categoryFantasy);

        Price price2 = Price.builder().currency(currency).value(40.00).build();
        priceRepository.save(price2);

        Book book2 = Book.builder().author(author2).coverType(CoverType.HARD).imageName("dayImage.jpg").description("" +
                "\n" +
                "Szczegóły\n" +
                "Mroczna strona zmysłowości. Jak wiele można zaryzykować w imię pożądliwej, szaleńczej miłości? Czy trudna przeszłość Evy i Gideona stanie na przeszkodzie do ich szczęścia?\n" +
                "\n" +
                "Gideon Cross. Na samo wspomnienie jego imienia moje ciało ogarniały fale pożądania i tęsknoty. Od chwili gdy go zobaczyłam, gdy po raz pierwszy zajrzałam poza oszałamiającą, nieziemsko piękną powierzchowność i dostrzegłam kryjącego się pod nią mrocznego, niebezpiecznego mężczyznę, poczułam nieodparte przyciąganie, jakbym odnalazła drugą połowę samej siebie. Potrzebowałam go jak bicia własnego serca, a on wystawił się na ogromne niebezpieczeństwo, zaryzykował dla mnie wszystko. " +
                "")
                .eanNumber(new BigInteger("9788381390149"))
                .price(price2)
                .publishDate(LocalDate.of(2019,1,30))
                .publisher(publisher2)
                .numberOfPages(415)
                .bookName("Wyznanie Crossa")
                .build();


        book2.setId(2l);
        categoryRomance.getBooks().add(book2);
        author2.getBooks().add(book2);
        book2.getCategories().add(categoryRomance);

        Price price3 = Price.builder().currency(currency).value(35.90).build();
        priceRepository.save(price3);

        Book book3 = Book.builder().author(author3).coverType(CoverType.SOFT).imageName("carrisiImage.jpg").description("" +
                "Gdy zapadają ciemności, pojawia się strach...\n" +
                "\n" +
                "\n" +
                "Gwałtowna burza doprowadza do awarii elektrowni i zmusza władze Rzymu do ogłoszenia dwudziestoczterogodzinnej przerwy w dostawie prądu. A już przed pięciuset laty papież Leon X w tajemniczej bulli ostrzegał, aby chronić Rzym przed pogrążeniem się w ciemnościach...\n" +
                "\n" +
                "\n" +
                "Nad miasto, w którym zapanowały panika i chaos, nadciąga w milczeniu mroczny cień pozostawiający za sobą szlak pełen trupów i... znaków. Może je odczytać tylko Marcus, łowca cieni przeszkolony w rozpoznawaniu anomalii występujących na miejscach zbrodni. Penitencjariusz utracił jednak swoją najcenniejszą broń – pamięć – co daje mordercy olbrzymią przewagę.")
                .eanNumber(new BigInteger("9788381254663"))
                .price(price3)
                .publishDate(LocalDate.of(2019,1,30))
                .publisher(publisher3)
                .numberOfPages(336)
                .bookName("Władca ciemności")
                .build();


        book3.setId(3l);
        categoryCriminal.getBooks().add(book3);
        author3.getBooks().add(book3);
        book3.getCategories().add(categoryCriminal);

        Price price4 = Price.builder().currency(currency).value(32.30).build();
        priceRepository.save(price4);

        Book book4 = Book.builder().author(author4).coverType(CoverType.HARD).imageName("obamaImage.jpg").description("" +
               "Osobiste wspomnienia najbardziej lubianej Pierwszej Damy Stanów Zjednoczonych\n" +
                "\n" +
                "\n" +
                "\n" +
                "Michelle Obama opisuje doświadczenia, które ją ukształtowały – od dzieciństwa w południowym Chicago, przez lata pracy na kierowniczym stanowisku, kiedy godziła macierzyństwo z karierą, aż do czasu spędzonego w najsłynniejszym domu świata. Pisząc z niebywałą szczerością, odwagą i humorem, odkrywa kulisy swojego życia rodzinnego. Opisuje, jak Obamowie znaleźli się w centrum zainteresowania światowych mediów i jak wyglądało ich życie w Białym Domu przez osiem kluczowych lat, kiedy poznawała Amerykę, zaś Ameryka poznawała ją. To zaskakująco intymny rozrachunek życia kobiety wrażliwej i stanowczej, która konsekwentnie odmawiała spełniania oczekiwań innych i której historia zachęca, by pójść w jej ślady. ")
                .eanNumber(new BigInteger("9788326826306"))
                .price(price4)
                .publishDate(LocalDate.of(2019,2,13))
                .publisher(publisher2)
                .numberOfPages(500)
                .bookName("Becoming. Moja historia")
                .build();

        book4.setId(4l);
        categoryBiography.getBooks().add(book4);
        author4.getBooks().add(book4);
        book4.getCategories().add(categoryBiography);

        Price price5 = Price.builder().currency(currency).value(29.90).build();
        priceRepository.save(price5);

        Book book5 = Book.builder().author(author5).coverType(CoverType.SOFT).imageName("staszakImage.jpg").description("" +
               "Czyn zbrojny powstania wielkopolskiego nie ma szczęścia w trafianiu do zbiorowej świadomości Polaków. O ile legionowa maciejówka czy błękitny mundur armii Hallera są dość powszechnie znane, to przybrana biało-czerwoną rozetką feldmyca czy późniejsza wielkopolska rogatywka wciąż muszą przebijać swą drogę do narodowej pamięci. Podobnie jest z powstańczymi bitwami. Trwające ponad półtora miesiąca walki, w których uczestniczyła artyleria i pociągi pancerne, ciągle nie są powszechnie znane, a nierzadko niewiele wiedzą o nich nawet mieszkańcy miejscowości, o które toczył się bój.\n" +
                "\n" +
                "\n" +
                "\n" +
                "Problem ten w znacznym stopniu został zauważony już w okresie międzywojennym. Powstałe wówczas Towarzystwo do Badania Historii Powstania Wielkopolskiego rozpoczęło działalność wydawniczą zmierzającą do zmiany tego stanu rzeczy. Jego staraniem została wydane m.in. obszerna monografia drugiej bitwy o Szubin ze stycznia 1919r.")
                .eanNumber(new BigInteger("9788311155985"))
                .price(price5)
                .publishDate(LocalDate.of(2019,1,17))
                .publisher(publisher1)
                .numberOfPages(500)
                .bookName("Rawicz 1919 ")
                .build();


        book5.setId(5l);
        author5.getBooks().add(book5);
        book5.getCategories().add(categoryHistory);
        categoryHistory.getBooks().add(book1);


        Price price6 = Price.builder().currency(currency).value(33.90).build();
        priceRepository.save(price6);

        Book book6 = Book.builder().author(author1).coverType(CoverType.SOFT).imageName("piekaraTom2Image.jpg").description(

                        "Zbiór sześciu opowiadań, w których ważną rolę pełnią niezwykłe kobiety: Katarzyna, Katrina, Roksana, Enya, Matylda, Valeria Flavia. To one będą miały wpływ na dzieje Świętego Oficjum. Drugi tom Płomienia i krzyża jest w odróżnieniu od tomu pierwszego książką dużo silniej umocowaną w świecie magicznym i nieco mniej zajmuje się rzeczywistą obyczajowością historyczną, bo jego bohaterowie głównie zajmują się problemami własnej, sekretnej społeczności. Nie znaczy to oczywiście, że pisząc tę powieść, nie przykładałem wagi do zachowania realiów znanych z historii naszego świata. Taki to już jest urok tworzenia historii alternatywnych, że muszą one zakładać, iż nie wszystko potoczyło się całkowicie inaczej. To, że mój świat nawiedzają demony, że funkcjonuje w nim magia, że są ludzie potrafiący penetrować mistyczne przestrzenie, nie oznacza przecież, że nie będzie w tym świecie sztuki i rzemiosła, że nie będzie w nim pieniądza (a co za tym idzie: ruchu kapitału), że ludzie nie będą się stroić, ozdabiać domów, jeść smacznych dań i pić wykwintnych lub mniej wykwintnych trunków, nie będą gromadzić bogactw. Nie oznacza też, że nie będą ze sobą walczyć za pomocą różnych rodzajów oręża."
        )
                .eanNumber(new BigInteger("9788327263353"))
                .price(price5)
                .publishDate(LocalDate.of(2016,03,11))
                .publisher(publisher1)
                .numberOfPages(444)
                .bookName("Płomień i krzyż. Tom 2")
                .build();


        book6.setId(6l);
        author1.getBooks().add(book6);
        book6.getCategories().add(categoryFantasy);
        categoryFantasy.getBooks().add(book6);


        Price price7 = Price.builder().currency(currency).value(29.90).build();
        priceRepository.save(price7);

        Book book7 = Book.builder().author(author1).coverType(CoverType.SOFT).imageName("piekaraTom1Image.jpg").description(

                "\n" +
                        "\n" +
                        "Oto on - Inkwizytor i Sługa Boży. Człowiek głębokiej wiary.\n" +
                        "\n" +
                        "Minęło tysiąc pięćset lat, od kiedy Jezus zszedł z krzyża, utopił we krwi Jerozolimę i zdobył Rzym. Światem żądzą inkwizytorzy.\n" +
                        "\n" +
                        "Płomień i Krzyż to symbole Świętego Officjum, instytucji niosącej w świat ogień jedynej i prawdziwej wiary. Ale to również dwa przeciwstawne sobie znaki - jeden symbolizuje perskich magów ognia, drugi walczących z nimi prawowiernych chrześcijan. Ich walka trwa.\n" +
                        "\n" +
                        "Losy inkwizytora Mordimera Madderdina widziane oczami między innymi Pięknej Katarzyny - matki Madderdina oraz Arnolda Lowefella - jego mentora i nauczyciela, członka Wewnętrznego Kręgu Inkwizytorium. A wszystko to w krwawej scenerii chłopskiego buntu pustoszącego Cesarstwo, w bogatym domu pięknej kurtyzany parającej się mroczną sztuką, w mistycznej siedzibie perskiego czarnoksiężnika, w Akademii Inkwizytorium oraz w siedzibie Świętego Officjum - tajemniczym klasztorze Amszilas.\n"
        )
                .eanNumber(new BigInteger("9788379643738"))
                .price(price7)
                .publishDate(LocalDate.of(2014,02,19))
                .publisher(publisher1)
                .numberOfPages(352)
                .bookName("Płomień i krzyż. Tom 1 ")
                .build();


        book7.setId(7l);
        author1.getBooks().add(book7);
        book7.getCategories().add(categoryFantasy);
        categoryFantasy.getBooks().add(book7);

        books.add(book1);
        books.add(book2);
        books.add(book3);
        books.add(book4);
        books.add(book5);
        books.add(book6);
        books.add(book7);

        return books;
    }
}

