package com.example.OrderManagement.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "order_detail")
public class OrderDetail {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderdetid;

    @Column(nullable = false, precision = 14, scale = 4)
    private BigDecimal price;

    @Column(nullable = false)
    private int orderqty;

    // Many-to-One with Order (to directly access the Order object)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderid", nullable = false)
    private Order order;

    // Many-to-One with Item (to directly access the Item object)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "itemno", referencedColumnName = "itemno", nullable = false)
    private Item item;

    // Default Constructor
    public OrderDetail() {}

    // Parameterized Constructor
    public OrderDetail(Order order, Item item, BigDecimal price, int orderqty) {
        this.order = order;
        this.item = item;
        this.price = price;
        this.orderqty = orderqty;
    }

    // Getters and Setters
    public int getOrderdetid() {
        return orderdetid;
    }

    public void setOrderdetid(int orderdetid) {
        this.orderdetid = orderdetid;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getOrderqty() {
        return orderqty;
    }

    public void setOrderqty(int orderqty) {
        this.orderqty = orderqty;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    // toString
    @Override
    public String toString() {
        return "OrderDetail{" +
                "orderdetid=" + orderdetid +
                ", price=" + price +
                ", orderqty=" + orderqty +
                ", orderid=" + order.getOrderid() +
                ", itemno=" + item.getItemno() +
                '}';
    }

    // equals and hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDetail that = (OrderDetail) o;
        return orderdetid == that.orderdetid 
                && orderqty == that.orderqty
                && price.compareTo(that.price) == 0 
                && Objects.equals(order, that.order)
                && Objects.equals(item, that.item);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderdetid, orderqty, price, order, item);
    }

}

