package com.abhishekdudi.moviecatalogservice.controller;

import com.abhishekdudi.moviecatalogservice.model.CatalogItem;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogController {

    @RequestMapping("/{id}")
    public List<CatalogItem> getCatalogs(@PathVariable(name = "id") String id) {
        return Collections.singletonList(
                new CatalogItem("Avengers", "Motion Picture", 10)
        );
    }
}
