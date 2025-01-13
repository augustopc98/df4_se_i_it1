package com.example.demo.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class CustomerOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long customerId;
    private Date orderDate;
    private String deliveryStatus;

    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderItem> items = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    private List<Payment> payments = new ArrayList<>();

    public CustomerOrder(Long id, Long customerId, Date orderDate, List<OrderItem> items) {
        this.id = id;
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.items = items;
    }

    public CustomerOrder() {
    }

    public void addOrderItem(OrderItem item) {
        this.items.add(item);
    }

    public void removeOrderItem(OrderItem item) {
        this.items.remove(item);
    }

    public List<OrderItem> getItems() {
        return items;
    }


    public BigDecimal calculateTotal() {
        return items.stream().map(OrderItem::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void applyDiscount(Discount discount) {
        BigDecimal totalAmount = calculateTotal();
        BigDecimal discountAmount = discount.applyDiscount(totalAmount);
        // Assume we subtract this discount from the first payment for simplicity
        if (!payments.isEmpty()) {
            Payment firstPayment = payments.get(0);
            firstPayment.setAmount(firstPayment.getAmount().subtract(discountAmount));
        }
    }

    public void sendForDelivery() {
        this.deliveryStatus = "In Transit";
    }

    public void updateDeliveryStatus(String status) {
        this.deliveryStatus = status;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public Long getCustomerId() {
        return customerId;
    }
}