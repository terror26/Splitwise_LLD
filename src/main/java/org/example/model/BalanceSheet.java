package org.example.model;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;


@Data
public class BalanceSheet {
    Map<UserInfo, Double> NameVsAmountMap = new HashMap<>();

}
