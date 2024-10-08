package com.example.OrderManagement.service;


import com.example.OrderManagement.model.Order;
import com.example.OrderManagement.model.OrderDetail;
import com.example.OrderManagement.model.Item;
import com.example.OrderManagement.model.ShipmentDetail;

import com.example.OrderManagement.repository.OrderRepository;
import com.example.OrderManagement.repository.OrderDetailRepository;
import com.example.OrderManagement.repository.ItemRepository;
import com.example.OrderManagement.repository.ShipmentDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private ItemRepository itemRepository;
    
    @Autowired
    private ShipmentDetailRepository shipmentDetailRepository;

    // 1. Create a new order with its details
    @Transactional
    public Order createNewOrder(Order order, List<OrderDetail> orderDetails) {
        // Save the order first
        Order savedOrder = orderRepository.save(order);

        // Save each order detail with a link to the saved order
        for (OrderDetail detail : orderDetails) {
            detail.setOrder(savedOrder);  // link the order
            orderDetailRepository.save(detail);  // save order detail
        }

        return savedOrder;
    }

    // 2. Add items to an existing order
    @Transactional
    public OrderDetail addItemToOrder(int orderId, String itemno, int quantity, BigDecimal price) {
        // Find the existing order
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        // Find the item by item number
        Item item = itemRepository.findByItemno(itemno)
                .orElseThrow(() -> new RuntimeException("Item not found"));

        // Create a new OrderDetail entry
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrder(order);  // link to the existing order
        orderDetail.setItem(item);  // link to the item
        orderDetail.setOrderqty(quantity);
        orderDetail.setPrice(price);

        // Save the new order detail
        return orderDetailRepository.save(orderDetail);
    }

    // 3. Confirm an order (update the confirmed flag)
    @Transactional
    public Order confirmOrder(int orderId) {
        // Find the order by its ID
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        // Update the confirmed flag
        order.setConfirmed(true);

        // Save the updated order
        return orderRepository.save(order);
    }
    
    // 4. Add item and process shipment with failure rollback
    @Transactional
    public void addItemAndShip(int orderId, String itemno, int orderqty, BigDecimal price, int shipqty) throws Exception {
        // Find the existing order
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        // Find the item by item number
        Item item = itemRepository.findByItemno(itemno)
                .orElseThrow(() -> new RuntimeException("Item not found"));

        // Create a new OrderDetail entry
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrder(order);  // link to the existing order
        orderDetail.setItem(item);  // link to the item
        orderDetail.setOrderqty(orderqty);
        orderDetail.setPrice(price);

        // Save the new order detail
        orderDetailRepository.save(orderDetail);

        // **Check if shipment quantity exceeds order quantity**
        if (shipqty > orderqty) {
            // **Throw an exception to trigger rollback**
            throw new Exception("Cannot ship more items than ordered. Transaction will be rolled back.");
        }

        // Create a new ShipmentDetail entry
        ShipmentDetail shipmentDetail = new ShipmentDetail(order.getOrderid(), orderDetail, item, price, shipqty);

        // Save the new shipment detail
        shipmentDetailRepository.save(shipmentDetail);
    }
}
