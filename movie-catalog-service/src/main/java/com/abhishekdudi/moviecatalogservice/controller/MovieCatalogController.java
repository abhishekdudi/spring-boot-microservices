package com.abhishekdudi.moviecatalogservice.controller;

import com.abhishekdudi.moviecatalogservice.model.CatalogItem;
import com.abhishekdudi.moviecatalogservice.model.Movie;
import com.abhishekdudi.moviecatalogservice.model.RatingDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/{id}")
    public List<CatalogItem> getCatalogs(@PathVariable(name = "id") String id) {

        // get all the rated movies by the user
        List<RatingDetails> ratings = Arrays.asList(
                new RatingDetails("1234", 10),
                new RatingDetails("5678", 7)
        );

        // for each movie, call movie info service and get details
        List<CatalogItem> catalogList = new ArrayList<>();

        for (RatingDetails ratingDetails : ratings) {
            Movie movie = restTemplate.getForObject("http://localhost:8082/movies/" + ratingDetails.getMovieId(), Movie.class);
            catalogList.add(new CatalogItem(movie.getName(), "Motion Picture", ratingDetails.getRating()));
        }

//        return Collections.singletonList(
//                new CatalogItem("Avengers", "Motion Picture", 10)
//        );
        return catalogList;
    }
}
