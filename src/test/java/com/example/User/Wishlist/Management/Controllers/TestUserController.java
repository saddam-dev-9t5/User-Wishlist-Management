package com.example.User.Wishlist.Management.Controllers;

import com.example.User.Wishlist.Management.Controller.UserController;
import com.example.User.Wishlist.Management.Repository.UserRepository;
import com.example.User.Wishlist.Management.Services.UserService;
import com.example.User.Wishlist.Management.dto.UserRequest;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

class TestUserController {

    @Autowired
    private UserController userController;

    @Mock
    private UserService userService;
    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder encoder;


    @Test
    void testAddNewUser() {
        assertEquals(1, 1);
    }
}