package tru.springframework.com.libaryapp.services;

import org.springframework.stereotype.Service;
import tru.springframework.com.libaryapp.model.Category;
import tru.springframework.com.libaryapp.repositories.CategoryRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService{

    CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getCategories() {
        List<Category> categories = new ArrayList<>();
        categoryRepository.findAll().iterator().forEachRemaining(categories::add);
        List<Category> sortedCategories = categories.stream().sorted(Comparator.comparing(Category::getId)).collect(Collectors.toList());

        return sortedCategories;
    }
}
