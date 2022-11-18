package org.kelvin.testing.orders.testingorders.controllers;

import org.kelvin.testing.orders.testingorders.models.Order;
import org.kelvin.testing.orders.testingorders.models.OrderProduct;
import org.kelvin.testing.orders.testingorders.models.Product;
import org.kelvin.testing.orders.testingorders.services.IOrderProductService;
import org.kelvin.testing.orders.testingorders.services.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://127.0.0.1:5173")
@RestController
@RequestMapping("/api/orders-products")
public class OrderProductController {
    @Autowired
    private IOrderProductService orderProductService;


    @GetMapping("/{id}")
    public List<Product> getAll(@PathVariable Long id){
        return  orderProductService.getProductsByOrderId(id);
    }
}
