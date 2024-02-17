package org.example.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Transaction {
    User paidBy;
    double amount;
    ExpenseType expenseType;
    List<Split> split;
}
