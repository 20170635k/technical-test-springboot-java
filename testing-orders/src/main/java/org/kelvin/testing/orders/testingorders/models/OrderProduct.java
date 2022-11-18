package org.kelvin.testing.orders.testingorders.models;

import javax.persistence.*;


@Entity
@Table(name = "tb_order_product")
public class OrderProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "id_product", referencedColumnName = "id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "id_order", referencedColumnName = "id")
    private Order order;

    private Integer quantity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

//    @Override
//    public boolean equals(Object obj) {
//        boolean ret = false;
//        if(this == obj){
//            ret = true;
//        }
//        if((obj instanceof OrderProduct)){
//            OrderProduct orderProduct =(OrderProduct)obj;
//
//            ret = this.productId !=null &&
//                    this.orderId != null &&
//                    this.productId == orderProduct.getProductId() &&
//                    this.orderId == orderProduct.getOrderId();
//        }
//        return ret;
//    }
}
