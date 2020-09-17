package com.abhishekdudi.moviecatalogservice.model;

import java.util.List;

public class UserRatingsList {

    public UserRatingsList() {
    }

    private List<RatingDetails> ratingsList;

    public List<RatingDetails> getUserRatings() {
        return ratingsList;
    }

    public void setUserRatings(List<RatingDetails> ratingsList) {
        this.ratingsList = ratingsList;
    }
}
