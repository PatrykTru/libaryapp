package tru.springframework.com.libaryapp.services;

import org.springframework.stereotype.Service;
import tru.springframework.com.libaryapp.commands.PublisherCommand;
import tru.springframework.com.libaryapp.converters.PublisherCommandToPublisher;
import tru.springframework.com.libaryapp.converters.PublisherToPublisherCommand;
import tru.springframework.com.libaryapp.model.Publisher;
import tru.springframework.com.libaryapp.repositories.PublisherRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PublisherServiceImpl implements PublisherService {

    private final PublisherRepository publisherRepository;
    private final PublisherToPublisherCommand publisherToPublisherCommand;
    private final PublisherCommandToPublisher publisherCommandToPublisher;

    public PublisherServiceImpl(PublisherRepository publisherRepository, PublisherToPublisherCommand publisherToPublisherCommand, PublisherCommandToPublisher publisherCommandToPublisher) {
        this.publisherRepository = publisherRepository;
        this.publisherToPublisherCommand = publisherToPublisherCommand;
        this.publisherCommandToPublisher = publisherCommandToPublisher;
    }

    @Override
    public List<Publisher> getPublishers() {
        List<Publisher> publishers = new ArrayList<>();
        publisherRepository.findAll().iterator().forEachRemaining(publishers::add);
        List<Publisher> sortedPublishers = publishers.stream().sorted(Comparator.comparing(Publisher::getId)).collect(Collectors.toList());
        return sortedPublishers;
    }

    @Override
    public Publisher getPublisherById(Long id) {

        Optional<Publisher> publisherOptional = publisherRepository.findById(id);


        return publisherOptional.get();
    }

    @Override
    public PublisherCommand savePublisherCommand(PublisherCommand publisherCommand) {

        Publisher publisherToSave = publisherCommandToPublisher.convert(publisherCommand);
        publisherRepository.save(publisherToSave);
        PublisherCommand savedCommand = publisherToPublisherCommand.convert(publisherToSave);
        System.out.println(savedCommand.getBooks().size());

        return savedCommand;
    }

    @Override
    public void deleteById(Long id) {
        publisherRepository.deleteById(id);
    }
}
