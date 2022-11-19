package org.kelvin.testing.orders.testingorders.services;

import org.kelvin.testing.orders.testingorders.models.entities.OrderProduct;

import java.util.List;
import java.util.Optional;

public interface IOrderProductService {
    List<OrderProduct> getProductsByOrderId(Long id);
    Optional<OrderProduct> getByProductOrderId(Long id);
    OrderProduct save( OrderProduct orderProduct);
    void deleteProductOrder(Long id);
}
