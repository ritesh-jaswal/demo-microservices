package com.project.user.service.UserService.services.impl;

import com.project.user.service.UserService.entities.Hotel;
import com.project.user.service.UserService.entities.Rating;
import com.project.user.service.UserService.entities.User;
import com.project.user.service.UserService.exceptions.ResourceNotFoundException;
import com.project.user.service.UserService.externalservices.HotelService;
import com.project.user.service.UserService.repositories.UserRepository;
import com.project.user.service.UserService.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService
{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;

    @Override
    public User saveUser(User user)
    {
        //generate  unique userid
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser()
    {
        List<User> users = userRepository.findAll();

        List<User> usersWithRatingsAndHotels = users.stream().peek(user ->
        {
            // Fetch ratings for the user
            Rating[] ratingsOfUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/" + user.getUserId(), Rating[].class);
            assert ratingsOfUser != null;
            List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();

            // Fetch hotel details for each rating
            List<Rating> ratingList = ratings.stream().peek(rating -> {
                // API call to hotel service to get the hotel
                Hotel hotel = hotelService.getHotel(rating.getHotelId());

                // Set the hotel to rating
                rating.setHotel(hotel);
            }).collect(Collectors.toList());
            // Set the ratings with hotel details to the user
            user.setRatings(ratingList);
        }).collect(Collectors.toList());

        return usersWithRatingsAndHotels;
    }

    @Override
    public User getUser(String userId)
    {
        //get user from database with the help  of user repository
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User","UserId",userId));

        // fetch rating of the above  user from RATING SERVICE
        Rating[] ratingsOfUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/"+user.getUserId(),Rating[].class);
        assert ratingsOfUser != null;
        List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();

        List<Rating> ratingList = ratings.stream().peek(rating ->
        {
            //api call to hotel service to get the hotel
            Hotel hotel = hotelService.getHotel(rating.getHotelId());

            //set the hotel to rating
            rating.setHotel(hotel);
            //return the rating
        }).collect(Collectors.toList());

        user.setRatings(ratingList);
        return user;
    }
}
