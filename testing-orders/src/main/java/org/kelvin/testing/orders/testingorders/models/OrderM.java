package org.kelvin.testing.orders.testingorders.models;

import java.util.List;

public class OrderM {
    private Long id;
    private String number;
    private String date;
    private List<OrderItem> orders;

    public OrderM(Long id, String number, String date, List<OrderItem> orders) {
        this.id = id;
        this.number = number;
        this.date = date;
        this.orders = orders;
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

    public List<OrderItem> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderItem> orders) {
        this.orders = orders;
    }
}
