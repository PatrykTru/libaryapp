package tru.springframework.com.libaryapp.services;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tru.springframework.com.libaryapp.model.Publisher;
import tru.springframework.com.libaryapp.repositories.PublisherRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class PublisherServiceImplTest {

    @Mock
    private PublisherService publisherService;

    @Mock
    private PublisherRepository publisherRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void getPublishers() {
        Publisher publisher = new Publisher();
        List<Publisher> testPublisher = new ArrayList<>();
        testPublisher.add(publisher);

        when(publisherService.getPublishers()).thenReturn(testPublisher);
        List categories = publisherService.getPublishers();

        assertEquals(categories.size(),1);
        verify(publisherService,times(1)).getPublishers();
        verify(publisherRepository,never()).findById(anyLong());
    }

    @Test
    public void getPublisherById() {
        Publisher publisher = new Publisher();
        publisher.setId(1l);


        when(publisherService.getPublisherById(anyLong())).thenReturn(publisher);


        Publisher categoryById = publisherService.getPublisherById(1l);

        assertNotNull("Null Recipe Returned", categoryById);
        verify(publisherService,times(1)).getPublisherById(anyLong());
        verify(publisherService,never()).getPublishers();
    }
}