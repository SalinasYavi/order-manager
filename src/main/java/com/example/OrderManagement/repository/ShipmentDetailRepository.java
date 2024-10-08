package com.example.OrderManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.OrderManagement.model.ShipmentDetail;
import java.util.List;

@Repository
public interface ShipmentDetailRepository extends JpaRepository<ShipmentDetail, Integer> {

    // Custom query to retrieve all shipment details by order ID
    List<ShipmentDetail> findByOrderDetailOrderOrderid(Integer orderid);
}
