package org.kelvin.testing.orders.testingorders.repositories;

import org.kelvin.testing.orders.testingorders.models.OrderProduct;
import org.kelvin.testing.orders.testingorders.models.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderProductRepository extends CrudRepository<OrderProduct,Long> {

}
