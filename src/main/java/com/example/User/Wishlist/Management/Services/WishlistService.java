package com.example.User.Wishlist.Management.Services;

import com.example.User.Wishlist.Management.Entities.User;
import com.example.User.Wishlist.Management.Repository.UserRepository;
import com.example.User.Wishlist.Management.dto.WishlistRequest;
import com.example.User.Wishlist.Management.dto.WishlistResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WishlistService {

    @Autowired
    private UserRepository userRepository;

    public List<WishlistResponse> getUserWishList() {

        return null;
    }

    public String addWishlist(WishlistRequest wishlistRequest) {
        return "";
    }
}
