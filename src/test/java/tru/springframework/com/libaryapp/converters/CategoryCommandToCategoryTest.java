package tru.springframework.com.libaryapp.converters;

import org.junit.Before;
import org.junit.Test;
import tru.springframework.com.libaryapp.commands.BookCommand;
import tru.springframework.com.libaryapp.commands.CategoryCommand;
import tru.springframework.com.libaryapp.model.Book;
import tru.springframework.com.libaryapp.model.Category;



import static org.junit.Assert.*;

public class CategoryCommandToCategoryTest {

    private final static Long ID_VALUE = 1L;
    private final static Long BOOK_ID = 2L;
    private final static String DESCRIPTION= "Fantasy";

    private CategoryCommandToCategory categoryCommandToCategory;

    @Before
    public void setUp() {

        categoryCommandToCategory = new CategoryCommandToCategory();
    }

    @Test
    public void testNullObject() {
        assertNull(categoryCommandToCategory.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(categoryCommandToCategory.convert(new CategoryCommand()));
    }
    @Test
    public void convert() {
        CategoryCommand categoryCommand = new CategoryCommand();
        categoryCommand.setId(ID_VALUE);
        categoryCommand.setDescription(DESCRIPTION);
        Book book = new Book();
        book.setId(BOOK_ID);
        categoryCommand.getBooks().add(book);

        Category category = categoryCommandToCategory.convert(categoryCommand);

        assertEquals(ID_VALUE , category.getId());
        assertEquals(DESCRIPTION , category.getDescription());
        assertEquals(1, category.getBooks().size());

    }
}