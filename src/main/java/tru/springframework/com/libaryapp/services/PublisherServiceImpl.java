package tru.springframework.com.libaryapp.services;

import org.springframework.stereotype.Service;
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

    public PublisherServiceImpl(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
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
}
