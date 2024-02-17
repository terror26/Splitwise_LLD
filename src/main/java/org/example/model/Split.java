package org.example.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Split {

    UserInfo user;
    double percentage;
    double amount;
}
