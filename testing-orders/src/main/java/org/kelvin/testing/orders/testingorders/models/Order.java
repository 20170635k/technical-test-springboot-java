package org.kelvin.testing.orders.testingorders.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "tb_orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String number;
    private String date;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "order")
    private List<OrderProduct> registrations;

    public Order(){
        registrations = new ArrayList<>();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<OrderProduct> getRegistrations() {
        return registrations;
    }

    public void setRegistrations(List<OrderProduct> registrations) {
        this.registrations = registrations;
    }

    public void addOrderProduct(OrderProduct orderProduct){
        registrations.add(orderProduct);
    }

    public void removeOrderProduct(OrderProduct orderProduct){
        registrations.remove(orderProduct);
    }

    public List<Product> getProducts(){
        return getRegistrations()
                .stream()
                .map(registrations ->registrations.getProduct())
                .collect(Collectors.toList());
    }
}
