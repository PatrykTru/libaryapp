package tru.springframework.com.libaryapp.converters;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import tru.springframework.com.libaryapp.commands.PublisherCommand;
import tru.springframework.com.libaryapp.model.Publisher;

@Component
public class PublisherToPublisherCommand implements Converter<Publisher, PublisherCommand> {


    @Nullable
    @Synchronized
    @Override
    public PublisherCommand convert(Publisher publisher) {

        if(publisher==null)
        return null;

        PublisherCommand publisherCommand = new PublisherCommand();

        publisherCommand.setBooks(publisher.getBooks());
        publisherCommand.setId(publisher.getId());
        publisherCommand.setPublisherName(publisher.getPublisherName());

        return publisherCommand;
    }
}
