package org.kelvin.testing.orders.testingorders.models.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String number;
    private String date;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true, mappedBy = "order")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
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

    public List<OrderProduct> getProducts(){
        return getRegistrations();
    }
}
