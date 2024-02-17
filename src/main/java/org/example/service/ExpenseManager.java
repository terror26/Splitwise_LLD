package org.example.service;

import org.example.model.BalanceSheet;
import org.example.model.ExpenseType;
import org.example.model.Transaction;
import org.example.service.impl.EqualExpenseManagerImpl;

public abstract class ExpenseManager {
    abstract public BalanceSheet createBalance(Transaction transaction, BalanceSheet balanceSheet);
    static ExpenseManager getInstance(ExpenseType expenseType) {
        if(ExpenseType.EQUALLY.equals(expenseType)) {
            return EqualExpenseManagerImpl.getInstance();
        }
        return null;
    };
}
