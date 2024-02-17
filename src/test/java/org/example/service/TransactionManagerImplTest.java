package org.example.service;

import org.example.model.ExpenseType;
import org.example.model.Transaction;
import org.example.model.User;
import org.example.model.UserInfo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class TransactionManagerImplTest {

    @Test
    public void createBalanceSheet() {
        TransactionManagerImpl transactionManager = new TransactionManagerImpl();

        User K = User.builder().userInfo(UserInfo.builder().name("K").build()).build();
        User A = User.builder().userInfo(UserInfo.builder().name("A").build()).build();
        User B = User.builder().userInfo(UserInfo.builder().name("B").build()).build();
        User C = User.builder().userInfo(UserInfo.builder().name("C").build()).build();

        K.addFriend(Arrays.asList(A,B,C));
        A.addFriend(Arrays.asList(K,B,C));
        B.addFriend(Arrays.asList(A,K,C));
        C.addFriend(Arrays.asList(A,B,K));

        Transaction t1 = Transaction.builder().paidBy(K).amount(1000).expenseType(ExpenseType.EQUALLY).build();
        Transaction t2 = Transaction.builder().paidBy(A).amount(4000).expenseType(ExpenseType.EQUALLY).build();
        Transaction t3 = Transaction.builder().paidBy(B).amount(8000).expenseType(ExpenseType.EQUALLY).build();
        Transaction t4 = Transaction.builder().paidBy(C).amount(12000).expenseType(ExpenseType.EQUALLY).build();

        transactionManager.createBalanceSheet(Arrays.asList(t1));
        transactionManager.createBalanceSheet(Arrays.asList(t1,t2));
        transactionManager.createBalanceSheet(Arrays.asList(t1,t2,t3));
        transactionManager.createBalanceSheet(Arrays.asList(t1,t2,t3,t4));
    }
}