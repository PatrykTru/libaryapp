package tru.springframework.com.libaryapp.services;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    void saveImageFile(Long bookId, MultipartFile multipartFile);
}
