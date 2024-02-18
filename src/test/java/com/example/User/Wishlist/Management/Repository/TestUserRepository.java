package com.example.User.Wishlist.Management.Repository;

import com.example.User.Wishlist.Management.Entities.User;
import com.example.User.Wishlist.Management.Services.UserService;
import com.example.User.Wishlist.Management.dto.UserRequest;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TestUserRepository {

    @Autowired
    private UserRepository userRepository;

    @Test
    void testFindByUsername() {
        User user = new User();
        user.setUsername("John");
        user.setPassword("john");
        user.setRoles("ROLE_USER");
        userRepository.save(user);

        Optional<User> optionalUser = userRepository.findByUsername("John");
        User actualUser = optionalUser.get();

        assertEquals("john", actualUser.getPassword());
    }
}
