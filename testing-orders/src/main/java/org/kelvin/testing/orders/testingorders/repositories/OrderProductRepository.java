package org.kelvin.testing.orders.testingorders.repositories;

import org.kelvin.testing.orders.testingorders.models.entities.OrderProduct;
import org.springframework.data.repository.CrudRepository;

public interface OrderProductRepository extends CrudRepository<OrderProduct,Long> {

}
