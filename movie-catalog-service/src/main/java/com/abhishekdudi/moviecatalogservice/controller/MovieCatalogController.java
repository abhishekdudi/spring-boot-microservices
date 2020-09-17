package com.abhishekdudi.moviecatalogservice.controller;

import com.abhishekdudi.moviecatalogservice.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/{userId}")
    public CatalogList getCatalogs(@PathVariable(name = "userId") String userId) {

        // get all the rated movies by the user
        UserRatingsList ratings = restTemplate.getForObject("http://localhost:8083/ratings/users/"+ userId, UserRatingsList.class);

        List<CatalogItem> itemList = new ArrayList<>();

        for (RatingDetails ratingDetails : ratings.getUserRatings()) {
            // for each movie, call movie info service and get details
            Movie movie = restTemplate.getForObject("http://localhost:8082/movies/" + ratingDetails.getMovieId(), Movie.class);

            // put them together
            itemList.add(new CatalogItem(movie.getName(), "Motion Picture", ratingDetails.getRating()));
        }

        CatalogList catalogList = new CatalogList();
        catalogList.setCatalogItemList(itemList);

//        return Collections.singletonList(
//                new CatalogItem("Avengers", "Motion Picture", 10)
//        );
        return catalogList;
    }
}
