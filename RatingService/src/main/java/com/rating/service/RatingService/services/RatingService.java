package com.rating.service.RatingService.services;

import com.rating.service.RatingService.entities.Rating;

import java.util.List;

public interface RatingService
{
    //create
    Rating create(Rating rating);

    //get all ratings
    List<Rating> getRatings();

    //get all by UserId
    List<Rating> getRatingByUserId(String userId);

    //get all by hotel
    List<Rating> getRatingByHotelId(String hotelId);
}
