package tru.springframework.com.libaryapp.converters;

import org.junit.Before;
import org.junit.Test;
import tru.springframework.com.libaryapp.commands.CategoryCommand;
import tru.springframework.com.libaryapp.model.Book;
import tru.springframework.com.libaryapp.model.Category;

import static org.junit.Assert.*;

public class CategoryToCategoryCommandTest {
    private final static Long ID_VALUE = 1L;
    private final static Long BOOK_ID = 2L;
    private final static String DESCRIPTION= "Fantasy";

    private CategoryToCategoryCommand categoryToCategoryCommand;

    @Before
    public void setUp() {

    categoryToCategoryCommand = new CategoryToCategoryCommand();
    }

    @Test
    public void testNullObject() {
        assertNull(categoryToCategoryCommand.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(categoryToCategoryCommand.convert(new Category()));
    }
    @Test
    public void convert() {
        Category category = new Category();
        category.setId(ID_VALUE);
        category.setDescription(DESCRIPTION);
        Book book = new Book();
        book.setId(BOOK_ID);
        category.getBooks().add(book);

        CategoryCommand categoryCommand = categoryToCategoryCommand.convert(category);

        assertEquals(ID_VALUE, categoryCommand.getId());
        assertEquals(DESCRIPTION, categoryCommand.getDescription());
    }
}