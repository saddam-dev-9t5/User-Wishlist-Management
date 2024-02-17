package com.example.User.Wishlist.Management.Services;

import com.example.User.Wishlist.Management.Entities.User;
import com.example.User.Wishlist.Management.Entities.Wishlist;
import com.example.User.Wishlist.Management.Repository.UserRepository;
import com.example.User.Wishlist.Management.Repository.WishlistRepository;
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

    @Autowired
    private WishlistRepository wishlistRepository;

    public List<Wishlist> getUserWishList() throws Exception {
        // Get login user
        try {
            User user = getLoginUser();
            return user.getWishlistList();
        }
        catch (Exception e) {
            throw new Exception("Wishlist not found");
        }
    }

    public String addWishlist(WishlistRequest wishlistRequest) {
        // Get login user
        User user = getLoginUser();
        if(user == null) {
            return "User not found";
        }

        // Store wishlist to DB
        Wishlist wishlist = Wishlist.builder()
                .name(wishlistRequest.getName())
                .priority(wishlistRequest.getPriority())
                .user(user)
                .build();
        wishlist = wishlistRepository.save(wishlist);

        return wishlist.getName() + " wishlist added successfully to " + wishlist.getUser().getUsername();
    }

    public String removeWishlist(Integer id) {
        // Get wishlist detail
        Optional<Wishlist> optionalWishlist = wishlistRepository.findById(id);
        if(optionalWishlist.isEmpty()) return "Invalid Wishlist Id";
        Wishlist wishlist = optionalWishlist.get();

        // Get user detail
        User user = getLoginUser();
        if(user == null) return "User not found";
        if(!user.getWishlistList().contains(wishlist)) {
            return "Wishlist item belonging to other user";
        }
        // remove wishList item from user table and delete from wishlist table
        user.getWishlistList().remove(wishlist);
        wishlistRepository.deleteById(id);
        return "Wishlist item removed";
    }

    public User getLoginUser() {
        User user = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserInfoDetails currUser = (UserInfoDetails) authentication.getPrincipal();
        String username = currUser.getUsername();

        Optional<User> optionalUser = userRepository.findByUsername(username);
        if(optionalUser.isPresent()) {
            user = optionalUser.get();
        }
        return user;
    }
}

