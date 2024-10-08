package com.example.OrderManagement.model;

import jakarta.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderid;

    @Column(nullable = false, length = 45)
    private String custname;

    @Column(nullable = false, length = 45)
    private String custcontact;

    @Column(nullable = false, length = 45)
    private String phonenumber;

    @Column(nullable = false, length = 45)
    private String email;

    @Column(nullable = false)
    private boolean confirmed;

    @Column(nullable = false, length = 45)
    private String enteredby;

    // One-to-Many with OrderDetail
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private Set<OrderDetail> orderDetails;

    // Default Constructor
    public Order() {}

    // Parameterized Constructor
    public Order(String custname, String custcontact, String phonenumber, String email, boolean confirmed, String enteredby) {
        this.custname = custname;
        this.custcontact = custcontact;
        this.phonenumber = phonenumber;
        this.email = email;
        this.confirmed = confirmed;
        this.enteredby = enteredby;
    }

    // Getters and Setters
    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public String getCustname() {
        return custname;
    }

    public void setCustname(String custname) {
        this.custname = custname;
    }

    public String getCustcontact() {
        return custcontact;
    }

    public void setCustcontact(String custcontact) {
        this.custcontact = custcontact;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public String getEnteredby() {
        return enteredby;
    }

    public void setEnteredby(String enteredby) {
        this.enteredby = enteredby;
    }

    public Set<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(Set<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    // toString
    @Override
    public String toString() {
        return "Order{" +
                "orderid=" + orderid +
                ", custname='" + custname + '\'' +
                ", custcontact='" + custcontact + '\'' +
                ", phonenumber='" + phonenumber + '\'' +
                ", email='" + email + '\'' +
                ", confirmed=" + confirmed +
                ", enteredby='" + enteredby + '\'' +
                '}';
    }

    // equals and hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return orderid == order.orderid && email.equals(order.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderid, email);
    }
}


