package tru.springframework.com.libaryapp.converters;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import tru.springframework.com.libaryapp.commands.BookCommand;
import tru.springframework.com.libaryapp.model.Book;
import tru.springframework.com.libaryapp.model.Category;

@Component
public class BookCommandToBook implements Converter<BookCommand, Book> {

    CategoryCommandToCategory categoryCommandToCategory;
    PublisherCommandToPublisher publisherCommandToPublisher;
    PriceCommandToPrice priceCommandToPrice;
    AuthorCommandToAuthor authorCommandToAuthor;

    public BookCommandToBook(CategoryCommandToCategory categoryCommandToCategory, PublisherCommandToPublisher publisherCommandToPublisher,
                             PriceCommandToPrice priceCommandToPrice, AuthorCommandToAuthor authorCommandToAuthor) {
        this.categoryCommandToCategory = categoryCommandToCategory;
        this.publisherCommandToPublisher = publisherCommandToPublisher;
        this.priceCommandToPrice = priceCommandToPrice;
        this.authorCommandToAuthor = authorCommandToAuthor;
    }

    @Override
    @Nullable
    @Synchronized
    public Book convert(BookCommand bookCommand) {
        if(bookCommand == null)
        return null;

        Book book = new Book();




        book.setAuthor(authorCommandToAuthor.convert(bookCommand.getAuthor()));
        book.setPrice(priceCommandToPrice.convert(bookCommand.getPrice()));
        book.setPublisher(publisherCommandToPublisher.convert(bookCommand.getPublisher()));
        book.setBookName(bookCommand.getBookName());
        book.setCoverType(bookCommand.getCoverType());
        book.setDescription(bookCommand.getDescription());
        book.setEanNumber(bookCommand.getEanNumber());
        book.setId(bookCommand.getId());
        book.setImage(bookCommand.getImage());
        book.setNumberOfPages(bookCommand.getNumberOfPages());
        book.setPublishDate(bookCommand.getPublishDate());





        if (bookCommand.getCategories() != null && bookCommand.getCategories().size() > 0){
            bookCommand.getCategories()
                    .forEach((Category category) -> book.getCategories().add(category));
        }

        return book;

    }
}
