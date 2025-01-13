package com.example.demo.service;

import com.example.demo.model.CustomerOrder;
import com.example.demo.model.Discount;
import com.example.demo.model.OrderItem;
import com.example.demo.repository.CustomerOrderRepository;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.OrderItemRepository;
import com.example.demo.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Date;


@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private CustomerOrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OrderItemRepository itemRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public void placeOrder(Long customerId, List<OrderItem> items) {
        CustomerOrder order = new CustomerOrder(null, customerId, new Date(), items);
        orderRepository.save(order);
    }

    @Override
    public void cancelOrder(Long orderId) {
        Optional<CustomerOrder> orderOpt = orderRepository.findById(orderId);
        orderOpt.ifPresent(orderRepository::delete);
    }

    @Override
    public void updateOrder(Long orderId, List<OrderItem> updatedItems) {
        Optional<CustomerOrder> orderOpt = orderRepository.findById(orderId);
        if (orderOpt.isPresent()) {
            CustomerOrder order = orderOpt.get();
            order.getItems().clear();
            order.getItems().addAll(updatedItems);
            orderRepository.save(order);
        }
    }

    @Override
    public BigDecimal calculateOrderTotal(Long orderId) {
        return orderRepository.findById(orderId)
                .map(CustomerOrder::calculateTotal)
                .orElse(BigDecimal.ZERO);
    }

    @Override
    public void applyOrderDiscount(Long orderId, Discount discount) {
        orderRepository.findById(orderId).ifPresent(order -> {
            order.applyDiscount(discount);
            orderRepository.save(order);
        });
    }

    @Override
    public void initiateDelivery(Long orderId) {
        orderRepository.findById(orderId).ifPresent(order -> {
            order.sendForDelivery();
            orderRepository.save(order);
        });
    }

    @Override
    public void updateDeliveryStatus(Long orderId, String status) {
        orderRepository.findById(orderId).ifPresent(order -> {
            order.updateDeliveryStatus(status);
            orderRepository.save(order);
        });
    }
}