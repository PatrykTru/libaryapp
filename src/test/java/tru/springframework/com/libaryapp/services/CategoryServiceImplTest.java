package tru.springframework.com.libaryapp.services;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tru.springframework.com.libaryapp.model.Category;
import tru.springframework.com.libaryapp.repositories.CategoryRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

public class CategoryServiceImplTest {
    @Mock
    private CategoryService categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getCategories() {
        Category category = new Category();
        List<Category> testCategories = new ArrayList<>();
        testCategories.add(category);

        when(categoryService.getCategories()).thenReturn(testCategories);
        List categories = categoryService.getCategories();

        assertEquals(categories.size(),1);
        verify(categoryService,times(1)).getCategories();
        verify(categoryRepository,never()).findById(anyLong());



    }

    @Test
    public void findById() {

        Category category = new Category();
        category.setId(1l);
        Optional<Category> categoryOptional= Optional.of(category);

        when(categoryService.findById(anyLong())).thenReturn(category);


        Category categoryById = categoryService.findById(1l);

        assertNotNull("Null Recipe Returned", categoryById);
        verify(categoryService,times(1)).findById(anyLong());
        verify(categoryService,never()).getCategories();
    }
}