package com.example.OrderManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.OrderManagement.model.OrderDetail;
import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {

    // Custom query to retrieve all OrderDetail entries by orderid
    List<OrderDetail> findByOrderOrderid(Integer orderid);
}

