package com.example.demo.controller;

import com.example.demo.model.Discount;
import com.example.demo.model.OrderItem;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public void placeOrder(@RequestParam Long customerId, @RequestBody List<OrderItem> items) {
        orderService.placeOrder(customerId, items);
    }

    @DeleteMapping("/{orderId}")
    public void cancelOrder(@PathVariable Long orderId) {
        orderService.cancelOrder(orderId);
    }

    @PutMapping("/{orderId}")
    public void updateOrder(@PathVariable Long orderId, @RequestBody List<OrderItem> updatedItems) {
        orderService.updateOrder(orderId, updatedItems);
    }

    @GetMapping("/{orderId}/total")
    public BigDecimal calculateOrderTotal(@PathVariable Long orderId) {
        return orderService.calculateOrderTotal(orderId);
    }

    @PostMapping("/{orderId}/discount")
    public void applyOrderDiscount(@PathVariable Long orderId, @RequestBody Discount discount) {
        orderService.applyOrderDiscount(orderId, discount);
    }

    @PostMapping("/{orderId}/delivery")
    public void initiateDelivery(@PathVariable Long orderId) {
        orderService.initiateDelivery(orderId);
    }

    @PutMapping("/{orderId}/status")
    public void updateDeliveryStatus(@PathVariable Long orderId, @RequestParam String status) {
        orderService.updateDeliveryStatus(orderId, status);
    }
}