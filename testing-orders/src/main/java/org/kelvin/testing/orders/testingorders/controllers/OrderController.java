package org.kelvin.testing.orders.testingorders.controllers;

import org.kelvin.testing.orders.testingorders.models.OrderItem;
import org.kelvin.testing.orders.testingorders.models.OrderM;
import org.kelvin.testing.orders.testingorders.models.ProductM;
import org.kelvin.testing.orders.testingorders.models.entities.Order;
import org.kelvin.testing.orders.testingorders.models.entities.OrderProduct;
import org.kelvin.testing.orders.testingorders.models.entities.Product;
import org.kelvin.testing.orders.testingorders.services.IOrderProductService;
import org.kelvin.testing.orders.testingorders.services.IOrderService;
import org.kelvin.testing.orders.testingorders.services.IProductService;
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
    @Autowired
    private IProductService productService;
    @Autowired
    private IOrderProductService orderProductService;

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
    public ResponseEntity<?> post(@RequestBody OrderM order){

        Order newOrder = new Order();
        newOrder.setNumber(order.getNumber());
        newOrder.setDate(order.getDate());
        Order orderCreated = orderService.save((newOrder));
            for(OrderItem orderItem: order.getOrders()){
                    Product productTemp = productService.getById(orderItem.getProduct().getId()).get();
                    OrderProduct orderProductTemp = new OrderProduct();
                    orderProductTemp.setProduct(productTemp);
                    orderProductTemp.setOrder(orderCreated);
                    orderProductTemp.setQuantity(orderItem.getQuantity());
                    orderProductService.save(orderProductTemp);
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(orderCreated);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody OrderM order, @PathVariable Long id){

        Optional<Order> optionalOrder = orderService.getById(id);
        if(optionalOrder.isPresent()){
            Order orderUpdate = optionalOrder.get();
            orderUpdate.setNumber(order.getNumber());
            orderUpdate.setDate(order.getDate());

            for(OrderItem orderItem: order.getOrders()){
                if(orderItem.getId()!=0){
                    Product productTemp = productService.getById(orderItem.getProduct().getId()).get();
                    OrderProduct orderProductTemp = orderProductService.getByProductOrderId(orderItem.getId()).get();
                    orderProductTemp.setProduct(productTemp);
                    orderProductTemp.setQuantity(orderItem.getQuantity());
                    orderProductService.save(orderProductTemp);
                }else{
                    Product productTemp = productService.getById(orderItem.getProduct().getId()).get();
                    OrderProduct orderProductTemp = new OrderProduct();
                    orderProductTemp.setProduct(productTemp);
                    orderProductTemp.setOrder(orderUpdate);
                    orderProductTemp.setQuantity(orderItem.getQuantity());
                    orderProductService.save(orderProductTemp);
                }
            }
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
