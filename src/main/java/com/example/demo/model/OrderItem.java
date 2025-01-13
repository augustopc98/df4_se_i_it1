package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class OrderItem {
    @Id
    private Long id;
    private Long productId;
    private int quantity;
    private BigDecimal productPrice;

    public OrderItem(Long id, Long productId, int quantity, BigDecimal productPrice) {
        this.id = id;
        this.productId = productId;
        this.quantity = quantity;
        this.productPrice = productPrice;
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public BigDecimal getPrice() {
        return productPrice.multiply(BigDecimal.valueOf(quantity));
    }

    public boolean validateItem() {
        return quantity > 0 && productPrice.compareTo(BigDecimal.ZERO) > 0;
    }
}