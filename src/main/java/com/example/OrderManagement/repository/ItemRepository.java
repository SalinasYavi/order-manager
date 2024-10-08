package com.example.OrderManagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.OrderManagement.model.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

    // Custom query to find an item by its item number (itemno)
   Optional <Item> findByItemno(String itemno);

}

