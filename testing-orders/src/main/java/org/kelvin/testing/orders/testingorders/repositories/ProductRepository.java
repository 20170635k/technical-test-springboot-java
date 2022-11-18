package org.kelvin.testing.orders.testingorders.repositories;

import org.kelvin.testing.orders.testingorders.models.Product;
import org.springframework.data.repository.CrudRepository;


public interface ProductRepository extends CrudRepository<Product,Long> {

}
