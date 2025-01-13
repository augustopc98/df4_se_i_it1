package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Customer {
    @Id
    private Long customerId;
    private String customerAddress;
    private String customerEmail;

    public Customer(Long customerId, String customerAddress, String customerEmail) {
        this.customerId = customerId;
        this.customerAddress = customerAddress;
        this.customerEmail = customerEmail;
    }

    public Customer() {
    }

    public Long getCustomerId() {
        return customerId;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerAddress(String customerAddress) {
    }

    public void setCustomerEmail(String customerEmail) {
    }
}