package org.kelvin.testing.orders.testingorders.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import java.io.Serializable;


public class OrderItem implements Serializable {
    private Long id;
    private Integer quantity;
    private ProductM product;

    public OrderItem(Long id, Integer quantity, ProductM product) {
        this.id = id;
        this.quantity = quantity;
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public ProductM getProduct() {
        return product;
    }

    public void setProduct(ProductM product) {
        this.product = product;
    }
}
