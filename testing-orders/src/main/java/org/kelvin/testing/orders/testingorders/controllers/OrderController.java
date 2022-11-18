package org.kelvin.testing.orders.testingorders.controllers;

import org.kelvin.testing.orders.testingorders.models.OrderItem;
import org.kelvin.testing.orders.testingorders.models.OrderM;
import org.kelvin.testing.orders.testingorders.models.ProductM;
import org.kelvin.testing.orders.testingorders.models.entities.Order;
import org.kelvin.testing.orders.testingorders.services.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@CrossOrigin(origins = "http://127.0.0.1:5173")
@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private IOrderService orderService;

    @GetMapping
    public List<OrderM> getAll(){
        return orderService.getAll()
                .stream()
                .map(order -> new OrderM(
                        order.getId(),
                        order.getNumber(),
                        order.getDate(),
                        order.getProducts()
                                .stream()
                                .map(data -> new
                                        OrderItem(
                                        data.getId(),
                                        data.getQuantity(),
                                        new ProductM(
                                                data.getProduct().getId(),
                                                data.getProduct().getName(),
                                                data.getProduct().getPrice())
                                )
                                )
                                .collect(Collectors.toList())
                ))
                .collect(Collectors.toList());
    }

    //why generic? because the content could be a Product if exists, in the other case null if doesn't
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        Optional<Order> optionalOrder= orderService.getById(id);
        if(optionalOrder.isPresent()){

            Order order = optionalOrder.get();

            return ResponseEntity.ok(
                    new OrderM(
                                order.getId(),
                                order.getNumber(),
                                order.getDate(),
                                order.getProducts()
                                        .stream()
                                        .map(data -> new
                                                        OrderItem(
                                                        data.getId(),
                                                        data.getQuantity(),
                                                        new ProductM(
                                                                data.getProduct().getId(),
                                                                data.getProduct().getName(),
                                                                data.getProduct().getPrice())
                                                )
                                        )
                                        .collect(Collectors.toList())
                )
            );
        }
        return ResponseEntity.notFound().build();
    }
    @PostMapping
    public ResponseEntity<?> post(@RequestBody Order order){
        return  ResponseEntity.status(HttpStatus.CREATED).body(orderService.save(order)) ;
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Order order, @PathVariable Long id){

        Optional<Order> optionalOrder = orderService.getById(id);
        if(optionalOrder.isPresent()){
            Order orderUpdate = optionalOrder.get();
            orderUpdate.setNumber(order.getNumber());
            orderUpdate.setDate(order.getDate());
            return ResponseEntity.status(HttpStatus.CREATED).body(orderService.save(orderUpdate));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Optional<Order> optionalOrder = orderService.getById(id);
        if(optionalOrder.isPresent()){
            orderService.delete(id);
            return  ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
