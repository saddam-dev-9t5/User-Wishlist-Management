package com.example.User.Wishlist.Management.Controller;

import com.example.User.Wishlist.Management.CustomeException.UsernameNotFoundException;
import com.example.User.Wishlist.Management.Entities.User;
import com.example.User.Wishlist.Management.Services.JwtService;
import com.example.User.Wishlist.Management.Services.UserInfoDetails;
import com.example.User.Wishlist.Management.Services.UserService;
import com.example.User.Wishlist.Management.dto.AuthRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/welcome")
    public ResponseEntity<String> welcome() {
        String message = "Welcome to all without auth";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PostMapping("/add-user")
    public ResponseEntity<String> addNewUser(@RequestBody User user) {
        String result = userService.addUser(user);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping("/user-profile")
    @PreAuthorize("hasAnyAuthority('ROLE_USER')")
    public ResponseEntity<UserInfoDetails> userProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserInfoDetails currUser = (UserInfoDetails) authentication.getPrincipal();
        return new ResponseEntity<>(currUser, HttpStatus.OK);
    }

    @GetMapping("/admin-profile")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> adminProfile() {
        String res = "Welcome to Admin Profile Page";
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping("/generate-token")
    public ResponseEntity<String> authenticateAndGetToken(@RequestBody AuthRequest authRequest) throws UsernameNotFoundException {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if(authentication.isAuthenticated()) {
            String result = jwtService.generateToken(authRequest.getUsername());
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        }else {
            throw new UsernameNotFoundException("Invalid user request");
        }
    }

}
