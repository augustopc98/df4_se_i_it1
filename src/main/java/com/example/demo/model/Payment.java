package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Entity
public class Payment {
    @Id
    private Long paymentId;
    private BigDecimal amount;
    private Date paymentDate;
    private String paymentStatus;

    public Payment(Long paymentId, BigDecimal amount, Date paymentDate, String paymentStatus) {
        this.paymentId = paymentId;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.paymentStatus = paymentStatus;
    }

    public void processPayment() {
        this.paymentStatus = "Processed";
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }
}