package com.example.OrderManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.OrderManagement.model.Order;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    // Custom query to retrieve all orders by a particular customer's name
    List<Order> findByCustname(String custname);
}
