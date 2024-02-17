package com.example.User.Wishlist.Management.dto;

import com.example.User.Wishlist.Management.Enum.WishlistPriority;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WishlistRequest {
    private String name;
    private WishlistPriority priority;
}
