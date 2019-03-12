package tru.springframework.com.libaryapp.services;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import tru.springframework.com.libaryapp.model.Book;
import tru.springframework.com.libaryapp.repositories.BookRepository;

import java.io.IOException;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class ImageServiceImplTest {

    @Mock
    private BookRepository bookRepository;

    private ImageService imageService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        imageService = new ImageServiceImpl(bookRepository);
    }

    @Test
    public void saveImageFile() throws IOException {
        Long id = 1l;
        MultipartFile file = new MockMultipartFile("imagefile" , "texting.txt" , "text/plain" ,
                "Test Image File".getBytes());

        Book recipe = new Book();
        recipe.setId(id);
        Optional<Book> bookOptional = Optional.of(recipe);

        when(bookRepository.findById(anyLong())).thenReturn(bookOptional);

        ArgumentCaptor<Book> captor = ArgumentCaptor.forClass(Book.class);

        imageService.saveImageFile(recipe.getId(), file);

        verify(bookRepository, times(1)).save(captor.capture());
        Book savedRecipe = captor.getValue();
        assertEquals(file.getBytes().length , savedRecipe.getImage().length);
    }
}