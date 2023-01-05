package com.example.demo.controller;

import com.example.demo.service.UserService;
import com.example.demo.domain.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@Api(value="user controller")
@CrossOrigin(origins="*")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/getAll")
    @ApiOperation(value="get all users")
    public ResponseEntity<List<User>> getAllUsers() {
        try {
            log.info("[UserController] searching for all users");
            Optional<List<User>> allUsers = userService.getAllUsers();
            if (allUsers.get().isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            log.info("[UserController] {} users was found", allUsers.get().size());
            return new ResponseEntity<>(allUsers.get(), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
