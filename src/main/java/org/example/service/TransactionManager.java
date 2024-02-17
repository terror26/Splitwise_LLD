package org.example.service;

import org.example.model.Transaction;

import java.util.List;

public interface TransactionManager {

    void createBalanceSheet(List<Transaction> transactions);
}
