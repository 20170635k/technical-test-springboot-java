package org.kelvin.testing.orders.testingorders.services;

import org.kelvin.testing.orders.testingorders.models.Order;
import org.kelvin.testing.orders.testingorders.models.OrderProduct;
import org.kelvin.testing.orders.testingorders.models.Product;
import org.kelvin.testing.orders.testingorders.repositories.OrderProductRepository;
import org.kelvin.testing.orders.testingorders.repositories.OrderRepository;
import org.kelvin.testing.orders.testingorders.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderProductService implements IOrderProductService{

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderRepository orderRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Product> getProductsByOrderId(Long id) {
        System.out.println("is present" + id);
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if(optionalOrder.isPresent()){
            return  optionalOrder.get().getProducts();
        }
        return new ArrayList<>();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<OrderProduct> getByProductOrderId(Long id) {
        return Optional.empty();
    }

    @Override
    @Transactional
    public OrderProduct saveProductOrder(Long id, OrderProduct orderProduct) {
        return null;
    }

    @Override
    @Transactional
    public void deleteProductOrder(Long id) {

    }
}
