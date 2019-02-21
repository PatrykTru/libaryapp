package tru.springframework.com.libaryapp.converters;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import tru.springframework.com.libaryapp.commands.CategoryCommand;
import tru.springframework.com.libaryapp.model.Category;


@Component
public class CategoryToCategoryCommand  implements Converter<Category, CategoryCommand> {




    @Nullable
    @Synchronized
    @Override
    public CategoryCommand convert(Category category) {
        if(category == null)
        return null;

        CategoryCommand categoryCommand = new CategoryCommand();

        categoryCommand.setDescription(category.getDescription());
        categoryCommand.setId(category.getId());
        if(category.getBooks()!=null && category.getBooks().size()>0)
        {
            category.getBooks().forEach(book-> categoryCommand.getBooks().add(book));
        }


        return categoryCommand;
    }
}
