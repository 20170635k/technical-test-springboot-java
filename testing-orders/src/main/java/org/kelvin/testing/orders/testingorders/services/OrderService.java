package org.kelvin.testing.orders.testingorders.services;

import org.kelvin.testing.orders.testingorders.models.Order;
import org.kelvin.testing.orders.testingorders.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements  IOrderService{

    @Autowired
    private OrderRepository orderRepository;


    @Override
    @Transactional(readOnly = true)
    public List<Order> getAll() {
        return (List<Order>) orderRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Order> getById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    @Transactional
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        orderRepository.deleteById(id);
    }
}
