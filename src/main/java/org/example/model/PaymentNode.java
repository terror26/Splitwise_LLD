package org.example.model;

import lombok.Data;

@Data
public class PaymentNode {
    String name;
    Double amount;

    public PaymentNode(String name, double amount) {
        this.name = name;
        this.amount = amount;
    }
}
