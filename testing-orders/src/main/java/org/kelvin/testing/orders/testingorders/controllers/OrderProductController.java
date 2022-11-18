package org.kelvin.testing.orders.testingorders.controllers;

import org.kelvin.testing.orders.testingorders.models.OrderItem;
import org.kelvin.testing.orders.testingorders.models.OrderM;
import org.kelvin.testing.orders.testingorders.models.ProductM;
import org.kelvin.testing.orders.testingorders.models.entities.Order;
import org.kelvin.testing.orders.testingorders.services.IOrderProductService;
import org.kelvin.testing.orders.testingorders.services.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://127.0.0.1:5173")
@RestController
@RequestMapping("/api/orders-products")
public class OrderProductController {
    @Autowired
    private IOrderProductService orderProductService;
    @Autowired
    private IOrderService orderService;

    @GetMapping("/{id}")
    public void getAll(@PathVariable Long id){
        Order order = orderService.getById(id).get();

    }
}
