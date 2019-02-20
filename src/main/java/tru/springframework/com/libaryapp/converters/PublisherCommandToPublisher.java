package tru.springframework.com.libaryapp.converters;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import tru.springframework.com.libaryapp.commands.PublisherCommand;
import tru.springframework.com.libaryapp.model.Publisher;

@Component
public class PublisherCommandToPublisher  implements Converter<PublisherCommand, Publisher> {

    @Nullable
    @Synchronized
    @Override
    public Publisher convert(PublisherCommand publisherCommand) {
        if(publisherCommand==null)
        return null;

        Publisher publisher = new Publisher();

        publisher.setId(publisherCommand.getId());
        publisher.setPublisherName(publisherCommand.getPublisherName());
        publisher.getBooks().addAll(publisherCommand.getBooks());

        return publisher;
    }
}
