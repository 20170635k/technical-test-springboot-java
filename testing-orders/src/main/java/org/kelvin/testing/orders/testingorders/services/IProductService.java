package org.kelvin.testing.orders.testingorders.services;

import org.kelvin.testing.orders.testingorders.models.entities.Product;

import java.util.List;
import java.util.Optional;

// Business Logic
public interface IProductService {
    List<Product> getAll();
    Optional<Product> getById(Long id);
    Product save(Product product);
    void delete(Long id);
}
