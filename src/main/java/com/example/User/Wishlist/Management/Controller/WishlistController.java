package com.example.User.Wishlist.Management.Controller;

import com.example.User.Wishlist.Management.Entities.Wishlist;
import com.example.User.Wishlist.Management.Services.WishlistService;
import com.example.User.Wishlist.Management.dto.WishlistRequest;
import com.example.User.Wishlist.Management.dto.WishlistResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/wishlists")

public class WishlistController {

    @Autowired
    private WishlistService wishlistService;

    @GetMapping("view-wishlist")
    public ResponseEntity<List<Wishlist>> getUserWishlist() {
        List<Wishlist> responses = new ArrayList<>();
        responses = wishlistService.getUserWishList();
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    @PostMapping("/add-wishlist")
    public ResponseEntity<String> addWishlist(@RequestBody WishlistRequest wishlistRequest) {
        String result = wishlistService.addWishlist(wishlistRequest);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @DeleteMapping("/remove-wishlist")
    public ResponseEntity<String> removeWishlist(@RequestParam("id") Integer id) {
        String result = wishlistService.removeWishlist(id);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
}
