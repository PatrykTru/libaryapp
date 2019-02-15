package tru.springframework.com.libaryapp.repositories;

import org.springframework.data.repository.CrudRepository;
import tru.springframework.com.libaryapp.model.Category;

public interface CategoryRepository extends CrudRepository<Category,Long> {
}
