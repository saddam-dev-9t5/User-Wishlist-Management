package com.example.User.Wishlist.Management.Services;

import com.example.User.Wishlist.Management.Entities.User;
import com.example.User.Wishlist.Management.Repository.UserRepository;
import com.example.User.Wishlist.Management.Transform.UserTransform;
import com.example.User.Wishlist.Management.dto.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userDetail = userRepository.findByUsername(username);

        return userDetail.map(UserInfoDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found " + username));
    }


    // Add new user to DB 'users'
    public String addUser(UserRequest userRequest) {
        // Data Transform from UserRequest DTO to User Entity
        User user = UserTransform.transformUserRequestDtotoUserEntity(userRequest);
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
        return "User Added Successfully";
    }
}
