package tru.springframework.com.libaryapp.controllers;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tru.springframework.com.libaryapp.commands.BookCommand;
import tru.springframework.com.libaryapp.services.BookService;
import tru.springframework.com.libaryapp.services.ImageService;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ImageControllerTest {

    @Mock
    ImageService imageService;
    @Mock
    BookService bookService;

    MockMvc mockMvc;

    ImageController imageController;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        imageController = new ImageController(imageService, bookService);
        mockMvc = MockMvcBuilders.standaloneSetup(imageController).build();
    }

    @Test
    public void getFormGet() throws Exception {
        BookCommand recipeCommand = new BookCommand();
        recipeCommand.setId(1l);

        when(bookService.findByCommandId(anyLong())).thenReturn(recipeCommand);

        mockMvc.perform(get("/book/1/image"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("book"));

        verify(bookService, times(1)).findByCommandId(anyLong());
    }

    @Test
    public void handleImagePost() throws Exception {
        MockMultipartFile multipartFile = new MockMultipartFile("imagefile", "testing.txt", "text/plain",
                "Spring Framework Guru".getBytes());

        mockMvc.perform(multipart("/book/1/image").file(multipartFile))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string("Location", "/book/1/show"));

        verify(imageService, times(1)).saveImageFile(anyLong(), any());

    }

    @Test
    public void renderImageFromDB() throws Exception {
        BookCommand command = new BookCommand();
        command.setId(1l);

        String s = "fake image text";
        Byte[] bytesBoxed = new Byte[s.getBytes().length];

        int i = 0;

        for (byte primByte : s.getBytes()) {
            bytesBoxed[i++] = primByte;
        }

        command.setImage(bytesBoxed);

        when(bookService.findByCommandId(anyLong())).thenReturn(command);

        MockHttpServletResponse response = mockMvc.perform(get("/book/1/bookimage"))
                .andExpect(status().isOk())
                .andReturn().getResponse();

        byte[] responseBytes = response.getContentAsByteArray();

        assertEquals(s.getBytes().length, responseBytes.length);
    }

}
