package com.abhishekdudi.movieinfoservice.controller;

import com.abhishekdudi.movieinfoservice.model.Movie;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @RequestMapping("/{movieId}")
    public Movie getMovie(@PathVariable(name = "movieId") String id) {

        return new Movie(id, "Avengers");
    }
}
