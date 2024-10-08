package com.example.OrderManagement.model;

import java.math.BigDecimal;

import jakarta.persistence.*;

@Entity
@Table(name = "shipment_detail")
public class ShipmentDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int shipmentdetid;

    @Column(nullable = false, precision = 14, scale = 4)
    private BigDecimal price;

    @Column(nullable = false)
    private int shipqty;

    // Add direct mapping to `orderid`
    @Column(name = "orderid", nullable = false)
    private int orderid;

    // Many-to-One with OrderDetail
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderdetid", nullable = false)
    private OrderDetail orderDetail;

    // Many-to-One with Item
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "itemno", referencedColumnName = "itemno", nullable = false)
    private Item item;

    // Default Constructor
    public ShipmentDetail() {}

    // Parameterized Constructor
    public ShipmentDetail(int orderid, OrderDetail orderDetail, Item item, BigDecimal price, int shipqty) {
        this.orderid = orderid;  // Set orderid explicitly
        this.orderDetail = orderDetail;
        this.item = item;
        this.price = price;
        this.shipqty = shipqty;
    }

    // Getters and Setters
    public int getShipmentdetid() {
        return shipmentdetid;
    }

    public void setShipmentdetid(int shipmentdetid) {
        this.shipmentdetid = shipmentdetid;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getShipqty() {
        return shipqty;
    }

    public void setShipqty(int shipqty) {
        this.shipqty = shipqty;
    }

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public OrderDetail getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(OrderDetail orderDetail) {
        this.orderDetail = orderDetail;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return "ShipmentDetail{" +
                "shipmentdetid=" + shipmentdetid +
                ", price=" + price +
                ", shipqty=" + shipqty +
                ", orderid=" + orderid +
                ", orderdetid=" + orderDetail.getOrderdetid() +
                ", Itemno=" + item.getItemno() +
                '}';
    }
}
