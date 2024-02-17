package org.example.service;

import org.example.model.*;

import java.util.*;

public class TransactionManagerImpl implements TransactionManager {

    @Override
    public void createBalanceSheet(List<Transaction> transactions) {
        BalanceSheet balanceSheet = null;
        for (Transaction transaction : transactions) {
            if (ExpenseType.EQUALLY.equals(transaction.getExpenseType())) {
                ExpenseManager instance = ExpenseManager.getInstance(ExpenseType.EQUALLY);
                balanceSheet = instance.createBalance(transaction, balanceSheet);
            }
        }
        printBalanceSheet(balanceSheet);
    }

    private void printBalanceSheet(BalanceSheet balanceSheet) {
        PriorityQueue<PaymentNode> reciever = new PriorityQueue<>((o1, o2) -> o2.getAmount().compareTo(o1.getAmount()));
        PriorityQueue<PaymentNode> sender = new PriorityQueue<>((o1, o2) -> o2.getAmount().compareTo(o1.getAmount()));

        Map<UserInfo, Double> nameVsAmountMap = balanceSheet.getNameVsAmountMap();
        System.out.println("nameVsAmountMap = " + Arrays.toString(nameVsAmountMap.entrySet().toArray()));

        for (UserInfo userInfo : nameVsAmountMap.keySet()) {
            double amount = nameVsAmountMap.get(userInfo);
            if (amount > 0) {
                reciever.add(new PaymentNode(userInfo.getName(), amount));
            } else if (amount < 0) {
                sender.add(new PaymentNode(userInfo.getName(), Math.abs(amount)));
            }
        }

        System.out.println("reciever list = " + Arrays.toString(reciever.toArray()));
        System.out.println("senders list = " + Arrays.toString(sender.toArray()));
        // start picking max one and denoting the transfer required;
        while (reciever.size() > 0) {
            PaymentNode r = reciever.poll();
            PaymentNode s = sender.poll();
            double amount = Math.min(r.getAmount(), s.getAmount());
            r.setAmount(r.getAmount() - amount);
            s.setAmount(s.getAmount() - amount);
            System.out.println(s.getName() + " is required to send amount " + amount + " to the person" + r.getName());
            if (r.getAmount() != 0) {
                reciever.add(r);
            }
            if (s.getAmount() != 0) {
                sender.add(s);
            }
        }

        System.out.println("-------------------- ");
        System.out.println("");

    }
}



















