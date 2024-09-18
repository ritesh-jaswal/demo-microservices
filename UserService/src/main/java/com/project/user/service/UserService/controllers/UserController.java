package com.project.user.service.UserService.controllers;

import com.project.user.service.UserService.entities.User;
import com.project.user.service.UserService.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    //create
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user)
    {
        User user1 = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }

    @GetMapping("/{userId}")
//    @CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<User> getSingleUser(@PathVariable String userId)
    {
        logger.debug("Attempting to retrieve user with ID: {}", userId);
        User user = userService.getUser(userId);
        return ResponseEntity.ok(user);
    }

//    public ResponseEntity<User> ratingHotelFallback(String userId, Throwable ex)
//    {
//        logger.info("Fallback is executed because service is down : {}", ex.getMessage());
//        ex.printStackTrace();
//
//        User user = User.builder()
//                .email("dummy@gmail.com")
//                .name("Dummy")
//                .about("This user is created dummy because some service is down")
//                .userId("141234").build();
//
//        return new ResponseEntity<>(user, HttpStatus.SERVICE_UNAVAILABLE);
//    }

    @GetMapping
//    @CircuitBreaker(name = "allRatingHotelBreaker", fallbackMethod = "allRatingHotelFallback")
    public ResponseEntity<List<User>> getAllUser() {
        List<User> allUser = userService.getAllUser();
        return ResponseEntity.ok(allUser);
    }

//    public ResponseEntity<List<User>> allRatingHotelFallback(Throwable ex) {
//        logger.info("Fallback is executed because some service is down : {}", ex.getMessage());
//        ex.printStackTrace();
//
//        User user = User.builder()
//                .email("dummy@gmail.com")
//                .name("Dummy")
//                .about("This user is created dummy because some service is down")
//                .userId("141234").build();
//
//        List<User> users = new ArrayList<>();
//        users.add(user);
//
//        return new ResponseEntity<>(users, HttpStatus.SERVICE_UNAVAILABLE);
//    }
}