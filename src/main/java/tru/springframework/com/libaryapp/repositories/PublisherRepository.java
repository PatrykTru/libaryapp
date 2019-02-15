package tru.springframework.com.libaryapp.repositories;

import org.springframework.data.repository.CrudRepository;
import tru.springframework.com.libaryapp.model.Publisher;

public interface PublisherRepository extends CrudRepository<Publisher,Long> {
}
