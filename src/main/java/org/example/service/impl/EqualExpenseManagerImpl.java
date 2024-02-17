package org.example.service.impl;


import org.example.model.*;
import org.example.service.ExpenseManager;

import java.util.Map;

public class EqualExpenseManagerImpl extends ExpenseManager {

    private static ExpenseManager expenseManager = new EqualExpenseManagerImpl();


    public static ExpenseManager getInstance() {
        return expenseManager;
    }

    @Override
    public BalanceSheet createBalance(Transaction transaction, BalanceSheet balanceSheet) {
        double totalAmount = transaction.getAmount();
        int n = transaction.getPaidBy().getFriends().size() + 1; // all persons
        double amountEachPersonToPay = totalAmount / n;
        double extraAmountRichGuyPaid = totalAmount - amountEachPersonToPay; // paidByUser
        if (balanceSheet == null) {
            balanceSheet = new BalanceSheet();
        }
        Map<UserInfo, Double> nameVsAmountMap = balanceSheet.getNameVsAmountMap();
        updateNamevsAmountMap(nameVsAmountMap, transaction, amountEachPersonToPay, extraAmountRichGuyPaid);
        return balanceSheet;

    }

    private void updateNamevsAmountMap(Map<UserInfo, Double> nameVsAmountMap, Transaction transaction, double amountEachPersonToPay, double amountPaidByRichGuy) {
        UserInfo paidBy = transaction.getPaidBy().getUserInfo();
        initialize(nameVsAmountMap, transaction);

        // updateValues
        for (User user : transaction.getPaidBy().getFriends()) {
            Double amount = nameVsAmountMap.get(user.getUserInfo()) + (amountEachPersonToPay * -1);
            nameVsAmountMap.put(user.getUserInfo(), amount); // updated amount
        }
        nameVsAmountMap.put(paidBy, nameVsAmountMap.get(paidBy) + amountPaidByRichGuy);
    }

    private void initialize(Map<UserInfo, Double> nameVsAmountMap, Transaction transaction) {
        for (User user : transaction.getPaidBy().getFriends()) {
            if (nameVsAmountMap.get(user.getUserInfo()) == null) {
                nameVsAmountMap.put(user.getUserInfo(), 0D);
            }
        }
        if (nameVsAmountMap.get(transaction.getPaidBy().getUserInfo()) == null) {
            nameVsAmountMap.put(transaction.getPaidBy().getUserInfo(), 0D);
        }
    }
}
