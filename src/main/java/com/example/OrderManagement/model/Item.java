package com.example.OrderManagement.model;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "items", indexes = {
    @Index(name = "idx_itemno", columnList = "itemno")
})
public class Item {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int itemid;
    
    @Column(nullable = false, length = 45, unique = true)
    private String itemno;
    
    @Column(nullable = false, length = 45)
    private String description;

    // Default Constructor
    public Item() {}

    // Parameterized Constructor
    public Item(String itemno, String description) {
        this.itemno = itemno;
        this.description = description;
    }

    // Getters and Setters
    public int getItemid() {
        return itemid;
    }

    public void setItemid(int itemid) {
        this.itemid = itemid;
    }

    public String getItemno() {
        return itemno;
    }

    public void setItemno(String itemno) {
        this.itemno = itemno;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // toString
    @Override
    public String toString() {
        return "Item{" +
                "itemid=" + itemid +
                ", itemno='" + itemno + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    // equals and hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return itemid == item.itemid && itemno.equals(item.itemno);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemid, itemno);
    }

}


