package org.kelvin.testing.orders.testingorders.services;

import org.kelvin.testing.orders.testingorders.models.entities.Order;
import org.kelvin.testing.orders.testingorders.models.entities.OrderProduct;
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

    @Autowired
    private OrderProductRepository orderProductRepository;

    @Override
    @Transactional(readOnly = true)
    public List<OrderProduct> getProductsByOrderId(Long id) {
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
        return orderProductRepository.findById(id);
    }

    @Override
    @Transactional
    public OrderProduct save( OrderProduct orderProduct) {
        return orderProductRepository.save(orderProduct);
    }

    @Override
    @Transactional
    public void deleteProductOrder(Long id) {

    }
}
