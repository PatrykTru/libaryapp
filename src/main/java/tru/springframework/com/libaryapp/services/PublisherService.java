package tru.springframework.com.libaryapp.services;

import tru.springframework.com.libaryapp.commands.PublisherCommand;
import tru.springframework.com.libaryapp.model.Publisher;

import java.util.List;

public interface PublisherService {
    List<Publisher> getPublishers();

    Publisher getPublisherById(Long id);

    PublisherCommand savePublisherCommand(PublisherCommand publisherCommand);
    void deleteById(Long id);
}
