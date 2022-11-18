package org.kelvin.testing.orders.testingorders.services;

import org.kelvin.testing.orders.testingorders.models.Order;

import java.util.List;
import java.util.Optional;

public interface IOrderService {

    List<Order> getAll();
    Optional<Order> getById(Long id);
    Order save(Order order);
    void delete(Long id);
}
