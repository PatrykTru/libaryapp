package tru.springframework.com.libaryapp.controllers;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import tru.springframework.com.libaryapp.commands.BookCommand;
import tru.springframework.com.libaryapp.services.BookService;
import tru.springframework.com.libaryapp.services.ImageService;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
@Controller
public class ImageController {
    ImageService imageService;
    BookService bookService;

    public ImageController(ImageService imageService, BookService bookService) {
        this.imageService = imageService;
        this.bookService = bookService;
    }

    @GetMapping("book/{id}/image")
    public String showUploadForm(@PathVariable String id, Model model) {

        model.addAttribute("book" , bookService.findByCommandId(Long.valueOf(id)));

        return "book/imageuploadform";
    }

    @PostMapping("/book/{id}/image")
    public String handleImagePost(@PathVariable String id, @RequestParam("imagefile") MultipartFile multipartFile){

        imageService.saveImageFile(Long.valueOf(id),multipartFile);

        return "redirect:/book/" + id +"/show" ;
    }

    @GetMapping("book/{id}/bookimage")
    public void renderImageFromDB(@PathVariable String id, HttpServletResponse response) throws IOException
    {
        BookCommand bookCommand = bookService.findByCommandId(Long.valueOf(id));

        if(bookCommand!=null) {
            byte[] byteArray = new byte[bookCommand.getImage().length];

            int i = 0;

            for (Byte wrappedByte : bookCommand.getImage()) {
                byteArray[i++] = wrappedByte;
            }

            response.setContentType("image/jpeg");
            InputStream is = new ByteArrayInputStream(byteArray);
            IOUtils.copy(is , response.getOutputStream());
        }
    }
}
