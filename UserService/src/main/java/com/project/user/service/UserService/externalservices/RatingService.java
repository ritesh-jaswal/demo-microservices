package com.project.user.service.UserService.externalservices;


import com.project.user.service.UserService.entities.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "RATING-SERVICE")
public interface RatingService
{
//    GET
    @GetMapping("/ratings/{ratingId}")
    Rating getRating(@PathVariable("ratingId") String ratingId);

//    POST
//    @PostMapping("/ratings")
//    public ResponseEntity<Rating> createRating(Rating values);
//
//
//    PUT
//    @PutMapping("/ratings/{ratingId}")
//    public ResponseEntity<Rating> updateRating(@PathVariable("ratingId") String ratingId, Rating rating);
//
//    DELETE
//    @DeleteMapping("/ratings/{ratingId}")
//    public void deleteRating(@PathVariable String ratingId);
}
