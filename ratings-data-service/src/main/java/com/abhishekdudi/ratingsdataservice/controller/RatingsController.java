package com.abhishekdudi.ratingsdataservice.controller;

import com.abhishekdudi.ratingsdataservice.model.RatingDetails;
import com.abhishekdudi.ratingsdataservice.model.UserRatingsList;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingsController {

    @RequestMapping("/{movieId}")
    public RatingDetails getRating(@PathVariable(name = "movieId") String movieId) {

        return new RatingDetails(movieId, 10);
    }

    @RequestMapping("/users/{userId}")
    public UserRatingsList getUserRatings(@PathVariable(name = "userId") String userId) {

        List<RatingDetails> ratings = Arrays.asList(
                new RatingDetails("1234", 10),
                new RatingDetails("5678", 7)
        );

        UserRatingsList userRatingsList = new UserRatingsList();
        userRatingsList.setUserRatings(ratings);

        return userRatingsList;

    }
}
