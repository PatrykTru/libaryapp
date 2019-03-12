package tru.springframework.com.libaryapp.converters;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import tru.springframework.com.libaryapp.commands.CategoryCommand;
import tru.springframework.com.libaryapp.model.Category;

@Component
public class CategoryCommandToCategory implements Converter<CategoryCommand, Category> {

    private BookCommandToBook bookConverter;

    @Nullable
    @Synchronized
    @Override
    public Category convert(CategoryCommand categoryCommand) {

        if(categoryCommand== null)
        return null;



        Category category = new Category();

        category.setId(categoryCommand.getId());
        category.setDescription(categoryCommand.getDescription());
        if(categoryCommand.getBooks()!=null && categoryCommand.getBooks().size()>0)
        {
            categoryCommand.getBooks().forEach(book -> category.getBooks().add(book));
        }

        return category;
    }

}
