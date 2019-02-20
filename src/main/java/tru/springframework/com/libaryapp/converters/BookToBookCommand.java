package tru.springframework.com.libaryapp.converters;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import tru.springframework.com.libaryapp.commands.BookCommand;
import tru.springframework.com.libaryapp.model.Author;
import tru.springframework.com.libaryapp.model.Book;
import tru.springframework.com.libaryapp.model.Category;

@Component
public class BookToBookCommand  implements Converter <Book, BookCommand>{

    private final AuthorToAuthorCommand authorToAuthorCommand;
    private final CategoryToCategoryCommand categoryToCategoryCommand;
    private final PriceToPriceCommand priceToPriceCommand;
    private final PublisherToPublisherCommand publisherToPublisherCommand;

    public BookToBookCommand(AuthorToAuthorCommand authorToAuthorCommand, CategoryToCategoryCommand categoryToCategoryCommand,
                             PriceToPriceCommand priceToPriceCommand, PublisherToPublisherCommand publisherToPublisherCommand) {
        this.authorToAuthorCommand = authorToAuthorCommand;
        this.categoryToCategoryCommand = categoryToCategoryCommand;
        this.priceToPriceCommand = priceToPriceCommand;
        this.publisherToPublisherCommand = publisherToPublisherCommand;
    }

    @Nullable
    @Synchronized
    @Override
    public BookCommand convert(Book book) {
        if(book == null)
        return null;

        BookCommand bookCommand = new BookCommand();

        bookCommand.setAuthorCommand(authorToAuthorCommand.convert(book.getAuthor()));
        bookCommand.setPriceCommand(priceToPriceCommand.convert(book.getPrice()));
        bookCommand.setPublisherCommand(publisherToPublisherCommand.convert(book.getPublisher()));
        bookCommand.setBookName(book.getBookName());
        bookCommand.setCoverType(book.getCoverType());
        bookCommand.setDescription(book.getDescription());
        bookCommand.setEanNumber(book.getEanNumber());
        bookCommand.setId(book.getId());
        bookCommand.setImage(book.getImage());
        bookCommand.setNumberOfPages(book.getNumberOfPages());
        bookCommand.setPublishDate(book.getPublishDate());





        if (book.getCategories() != null && book.getCategories().size() > 0){
            book.getCategories()
                    .forEach((Category category) -> bookCommand.getCategories().add(categoryToCategoryCommand.convert(category)));
        }

        return bookCommand;

    }
}
