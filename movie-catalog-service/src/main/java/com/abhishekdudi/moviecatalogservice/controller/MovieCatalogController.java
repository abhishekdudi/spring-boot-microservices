package com.abhishekdudi.moviecatalogservice.controller;

import com.abhishekdudi.moviecatalogservice.model.CatalogItem;
import com.abhishekdudi.moviecatalogservice.model.CatalogList;
import com.abhishekdudi.moviecatalogservice.model.Movie;
import com.abhishekdudi.moviecatalogservice.model.UserRatingsList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/{userId}")
    public CatalogList getCatalogs(@PathVariable(name = "userId") String userId) {

        // get all the rated movies by the user
        // ratings-data-service as a service
//        UserRatingsList ratings = restTemplate.getForObject("http://localhost:8083/ratings/users/"+ userId, UserRatingsList.class);

        // as eureka client
        UserRatingsList ratings = restTemplate.getForObject("http://ratings-data-service/ratings/users/"+ userId, UserRatingsList.class);

        // without using streams
//        List<CatalogItem> itemList = new ArrayList<>();
//
//        for (RatingDetails ratingDetails : ratings.getUserRatings()) {
//            // for each movie, call movie info service and get details
//            Movie movie = restTemplate.getForObject("http://localhost:8082/movies/" + ratingDetails.getMovieId(), Movie.class);
//
//            // put them together
//            itemList.add(new CatalogItem(movie.getName(), "Motion Picture", ratingDetails.getRating()));
//        }

        // using streams
        List<CatalogItem> itemList = ratings.getUserRatings().stream().map(ratingDetails -> {

            // for each movie, call movie info service and get details
            // movie-info-service as a service
//            Movie movie = restTemplate.getForObject("http://localhost:8082/movies/" + ratingDetails.getMovieId(), Movie.class);

            // as eureka client
            Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + ratingDetails.getMovieId(), Movie.class);

            // put them together
            return new CatalogItem(movie.getName(), "Motion Picture", ratingDetails.getRating());
        }).collect(Collectors.toList());

        CatalogList catalogList = new CatalogList();
        catalogList.setCatalogItemList(itemList);

//        return Collections.singletonList(
//                new CatalogItem("Avengers", "Motion Picture", 10)
//        );
        return catalogList;
    }
}
