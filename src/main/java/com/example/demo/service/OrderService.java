package com.example.demo.service;

import com.example.demo.model.CustomerOrder;
import com.example.demo.model.Discount;
import com.example.demo.model.OrderItem;

import java.math.BigDecimal;
import java.util.List;

public interface OrderService {
    void placeOrder(Long customerId, List<OrderItem> items);
    void cancelOrder(Long orderId);
    void updateOrder(Long orderId, List<OrderItem> updatedItems);
    BigDecimal calculateOrderTotal(Long orderId);
    void applyOrderDiscount(Long orderId, Discount discount);
    void initiateDelivery(Long orderId);
    void updateDeliveryStatus(Long orderId, String status);
}