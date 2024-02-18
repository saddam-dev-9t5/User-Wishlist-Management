package com.example.User.Wishlist.Management.Services;
import static org.junit.jupiter.api.Assertions.*;

import com.example.User.Wishlist.Management.Repository.UserRepository;
import com.example.User.Wishlist.Management.dto.UserRequest;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class TestUserService {

    @Autowired
    private UserService userService;
    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder encoder;

    @Test
    public void testAddUser() {
        UserRequest userRequest = new UserRequest("Jahid", "jahid", "ROLE_ADMIN");
        try {
            String result = userService.addUser(userRequest);
            assertEquals("User Added Successfully", result);
        }
        catch (Exception e) {
            assertEquals("Please enter valid data", e.getMessage());
        }
    }

}
