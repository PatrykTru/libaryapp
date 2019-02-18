package tru.springframework.com.libaryapp.repositories;

import org.springframework.data.repository.CrudRepository;
import tru.springframework.com.libaryapp.model.Price;

public interface PriceRepository extends CrudRepository<Price,Long> {
}
