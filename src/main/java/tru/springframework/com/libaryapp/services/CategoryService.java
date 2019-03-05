package tru.springframework.com.libaryapp.services;

import tru.springframework.com.libaryapp.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getCategories();
    Category findById(Long id);
}
