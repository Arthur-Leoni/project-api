package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
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

import static com.example.demo.factory.UserFactory.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;


@ExtendWith(SpringExtension.class)
public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @Test
    void return_all_users_when_user_service_return_user() {
        List<User> userList = createUserList();
        ResponseEntity<List<User>> expectedReturn = new ResponseEntity<>(userList, HttpStatus.OK);
        when(userService.getAllUsers()).thenReturn(Optional.of(userList));

        ResponseEntity<List<User>> allUsers = userController.getAllUsers();

        assertEquals("",expectedReturn, allUsers);
        verify(userService).getAllUsers();
    }

    @Test
    void return_no_content_when_user_service_return_nothing() {
        List<User> userList = new ArrayList<User>();
        ResponseEntity<List<User>> expectedReturn = new ResponseEntity<>(HttpStatus.NO_CONTENT);

        when(userService.getAllUsers()).thenReturn(Optional.of(userList));

        ResponseEntity<List<User>> allUsers = userController.getAllUsers();
        assertEquals("",expectedReturn, allUsers);
        verify(userService).getAllUsers();
    }

    @Test
    void return_error_when_user_service_throws_exception() {
        ResponseEntity<List<User>> expectedReturn = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        when(userService.getAllUsers()).thenThrow(new RuntimeException());

        ResponseEntity<List<User>> allUsers = userController.getAllUsers();
        assertEquals("",expectedReturn, allUsers);
        verify(userService).getAllUsers();
    }
}
