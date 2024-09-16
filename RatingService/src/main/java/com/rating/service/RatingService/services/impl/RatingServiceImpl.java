package com.rating.service.RatingService.services.impl;

import com.rating.service.RatingService.entities.Rating;
import com.rating.service.RatingService.repositories.RatingRepository;
import com.rating.service.RatingService.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RatingServiceImpl implements RatingService
{
    @Autowired
    private RatingRepository repository;

    @Override
    public Rating create(Rating rating)
    {
        String ratingId = UUID.randomUUID().toString();
        rating.setRatingId(ratingId);
        return repository.save(rating);
    }

    @Override
    public List<Rating> getRatings() {
        return repository.findAll();
    }

    @Override
    public List<Rating> getRatingByUserId(String userId) {
        return repository.findByUserId(userId);
    }

    @Override
    public List<Rating> getRatingByHotelId(String hotelId) {
        return repository.findByHotelId(hotelId);
    }
}
