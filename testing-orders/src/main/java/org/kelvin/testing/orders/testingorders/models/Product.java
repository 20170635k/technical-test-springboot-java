package org.kelvin.testing.orders.testingorders.models;


import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "tb_products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Double price;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "product")
    private List<OrderProduct> registrations;

    public List<OrderProduct> getRegistrations() {
        return registrations;
    }

    public void setRegistrations(List<OrderProduct> registrations) {
        this.registrations = registrations;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<Order> getOrders(){
        return getRegistrations()
                .stream()
                .map(registrations ->registrations.getOrder())
                .collect(Collectors.toList());
    }
}
