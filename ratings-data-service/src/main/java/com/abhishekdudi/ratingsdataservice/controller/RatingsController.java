package com.abhishekdudi.ratingsdataservice.controller;

import com.abhishekdudi.ratingsdataservice.model.RatingDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ratings")
public class RatingsController {

    @RequestMapping("/{movieId}")
    public RatingDetails getRating(@PathVariable(name = "movieId") String movieId) {

        return new RatingDetails(movieId, 10);
    }
}
