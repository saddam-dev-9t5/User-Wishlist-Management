package com.example.User.Wishlist.Management.Controller;

import com.example.User.Wishlist.Management.CustomeException.UsernameNotFoundException;
import com.example.User.Wishlist.Management.Entities.User;
import com.example.User.Wishlist.Management.Services.JwtService;
import com.example.User.Wishlist.Management.Services.UserInfoDetails;
import com.example.User.Wishlist.Management.Services.UserService;
import com.example.User.Wishlist.Management.Services.WishlistService;
import com.example.User.Wishlist.Management.dto.AuthRequest;
import com.example.User.Wishlist.Management.dto.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private WishlistService wishlistService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    // Access without authentication
    @GetMapping("/welcome")
    public ResponseEntity<String> welcome() {
        String message = "Welcome to all without auth";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    // Register new user
    @PostMapping("/add-user")
    public ResponseEntity<String> addNewUser(@RequestBody UserRequest userRequest) {
        String result = userService.addUser(userRequest);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    // Access with authentication 'ROLE_USER'
    // view user info with User role
    @GetMapping("/user-profile")
    @PreAuthorize("hasAnyAuthority('ROLE_USER')")
    public ResponseEntity<User> userProfile() {
        User currUser = wishlistService.getLoginUser();
        return new ResponseEntity<>(currUser, HttpStatus.OK);
    }

    // Access with authentication 'ROLE_ADMIN'
    // view user info with admin role
    @GetMapping("/admin-profile")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> adminProfile() {
        String result = "Welcome to Admin Profile Page";
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // Authenticate User
    // Login or Generate token using credential
    @PostMapping("/generate-token")
    public ResponseEntity<String> authenticateAndGetToken(@RequestBody AuthRequest authRequest) throws UsernameNotFoundException {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
            if(authentication.isAuthenticated()) {
                String result = jwtService.generateToken(authRequest.getUsername());
                return new ResponseEntity<>(result, HttpStatus.CREATED);
            }else {
                throw new UsernameNotFoundException("Invalid user request");
            }
        }
        catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed: " + e.getMessage());
        }
    }

}
