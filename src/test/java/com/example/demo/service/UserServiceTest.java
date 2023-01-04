package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.demo.factory.UserFactory.createUserList;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;


@ExtendWith(SpringExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Test
    void return_all_users_when_user_repository_return_user() {
        List<User> userList = createUserList();
        Optional<List<User>> expectedReturn = Optional.of(userList);

        when(userRepository.getAllUsers()).thenReturn(Optional.of(userList));
        Optional<List<User>> allUsers = userService.getAllUsers();

        assertEquals("",expectedReturn, allUsers);
        verify(userRepository).getAllUsers();
    }

    @Test
    void throw_exception_when_user_repository_throws_exception() {
        when(userRepository.getAllUsers()).thenThrow(new RuntimeException());

        assertThrows(RuntimeException.class, () -> {
            userService.getAllUsers();
        });

        verify(userRepository).getAllUsers();
    }
}
