package com.example.User.Wishlist.Management.Transform;

import com.example.User.Wishlist.Management.Entities.User;
import com.example.User.Wishlist.Management.dto.UserRequest;

public class UserTransform {
    public static User transformUserRequestDtotoUserEntity(UserRequest userRequest) {
        User user = User.builder()
                .username(userRequest.getUserName())
                .password(userRequest.getUserPassword())
                .roles(userRequest.getRoles())
                .build();
        return user;
    }
}
