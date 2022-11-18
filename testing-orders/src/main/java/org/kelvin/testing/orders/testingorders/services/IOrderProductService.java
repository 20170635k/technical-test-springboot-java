package org.kelvin.testing.orders.testingorders.services;

import org.kelvin.testing.orders.testingorders.models.Order;
import org.kelvin.testing.orders.testingorders.models.OrderProduct;
import org.kelvin.testing.orders.testingorders.models.Product;

import java.util.List;
import java.util.Optional;

public interface IOrderProductService {
    List<Product> getProductsByOrderId(Long id);
    Optional<OrderProduct> getByProductOrderId(Long id);
    OrderProduct saveProductOrder(Long id, OrderProduct orderProduct);
    void deleteProductOrder(Long id);
}
