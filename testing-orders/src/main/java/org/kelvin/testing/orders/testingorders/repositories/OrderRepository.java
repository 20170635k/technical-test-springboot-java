package org.kelvin.testing.orders.testingorders.repositories;

import org.kelvin.testing.orders.testingorders.models.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order,Long> {
}
