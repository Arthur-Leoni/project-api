package com.example.demo.service;

import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static com.example.demo.factory.UserFactory.createUserList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


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

        assertEquals(expectedReturn, allUsers);
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
