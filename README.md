# User Wishlist Management #
This project implements the backend functionality for wishlist management using Java and Spring Boot. It provides RESTful API endpoints for wishlist management, integrates user authentication using Spring Security, and utilizes Spring Data JPA for database integration.

## Features ##
----------
**User Authentication:**\
Implemented user authentication using Spring Security.\
Allows users to sign up and log in securely to the system.\
http://localhost:8080/api/user/add-user: POST endpoint to to sign up user\
http://localhost:8080/api/user/generate-token: POST endpoint to to login/get token user\
**Wishlist Management:**\
Designed and implemented RESTful API endpoints for wishlist management.\
http://localhost:8080/api/wishlists/view-wishlist: GET endpoint to retrieve a user's wishlist.\
http://localhost:8080/api/wishlists/add-wishlist: POST endpoint to create a new wishlist item for login user.\
http://localhost:8080/api/wishlists/remove-wishlist?id={id}: DELETE endpoint to remove a wishlist item by ID.\
Ensures that only authenticated users can access and manage their wishlists.\
**Database Integration:**\
Integrated the application with a relational database using Spring Data JPA.\
Designed the database schema to store user information and wishlist items.\
Implemented repository interfaces for CRUD operations on wishlist entities.\
![image](https://github.com/saddam-dev-9t5/User-Wishlist-Management/assets/119750617/ac77d6c6-131d-4886-9dc0-61fb447e4968)\

## Clone the repository ##
git clone https://github.com/saddam-dev-9t5/User-Wishlist-Management.git\

## API Endpoints ##
GET /api/wishlists/view-wishlist: Retrive login user's wishlists.\
POST /api/wishlists/add-wishlist: Creates a new wishlist item.\
POST /api/user/add-user: Creates a new user.\
POST /api/user/generate-token Login user with credential.\ 
DELETE api/wishlists/remove-wishlist?id={id}: Removes a wishlist item by ID.\

## Database Schema MySQL ##
The database schema includes tables for storing user information and wishlist items. The exact schema may vary based on specific requirements.\
Users Table:\
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Column(unique = true, nullable = false)
    @NotBlank(message = "username is required")
    private String username;

    @NotBlank(message = "Password is required")
    private String password;

    @NotBlank(message = "Roles is required")
    private String roles;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Wishlist> wishlistList = new ArrayList<>();
Wishlist Item Table:\
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

## How to run the application ##
spring.datasource.url=jdbc:mysql://localhost:3306/`wishlist`?createTableIfNotExist=true\
spring.datasource.username=`root`\
spring.datasource.password=`saddam`\

change the above file `application.properties` database name, username and password at `/src/main/resources/application.properties`\

## POST MAN ##
https://www.postman.com/saddam802216/workspace/mkena/collection/6249529-df80e272-8b5d-453f-8ecb-fa8ed3bb1f89?action=share&creator=6249529&active-environment=6249529-6233223f-170e-44c1-ad16-200d4c6277bb
