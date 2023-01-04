package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {

    public Optional<List<User>> getAllUsers(){
        //simulation
        List<User> userList = new ArrayList<User>();

        User user1 = User.builder()
                .age("29")
                .name("Arthur")
                .build();

        User user2 = User.builder()
                .age("27")
                .name("Raul")
                .build();

        userList.add(user1);
        userList.add(user2);

        return Optional.of(userList);
    }
}
