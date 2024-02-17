package com.example.User.Wishlist.Management.Entities;

import com.example.User.Wishlist.Management.Enum.WishlistPriority;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "wishlist")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Wishlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Wishlist is requires")
    @Size(max = 100, message = "Wishlist name should be less then 100 character")
    private String name;

    private WishlistPriority priority;

    @JsonIgnore
    @JoinColumn
    @ManyToOne
    private User user;
}
