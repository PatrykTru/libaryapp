package tru.springframework.com.libaryapp.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import tru.springframework.com.libaryapp.model.Book;
import tru.springframework.com.libaryapp.repositories.BookRepository;

import java.io.IOException;

@Service
@Slf4j
public class ImageServiceImpl implements ImageService{

    BookRepository bookRepository;

    public ImageServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    @Transactional
    public void saveImageFile(Long bookId, MultipartFile multipartFile) {
        try {
            Book book = bookRepository.findById(bookId).get();

            Byte[] bytes = new Byte[multipartFile.getBytes().length];

            int i=0;

            for(Byte b : multipartFile.getBytes())
            {
                bytes[i++]= b;
            }

            book.setImage(bytes);
            bookRepository.save(book);
        }
        catch (IOException e)
        {
            //todo handle better
            log.debug("problem with imageFile");
            e.printStackTrace();
        }
    }
}
