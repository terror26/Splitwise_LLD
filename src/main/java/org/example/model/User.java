package org.example.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class User {
    UserInfo userInfo;
    List<User> friends;

    public void addFriend(List<User> friends) {
        this.friends = friends;
    }
}
