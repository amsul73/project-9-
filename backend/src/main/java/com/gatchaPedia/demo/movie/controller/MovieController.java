package com.gatchaPedia.demo.movie.controller;


import com.gatchaPedia.demo.movie.response.Top100MovieResponse;
import com.gatchaPedia.demo.movie.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;



    @GetMapping("/movies/top100")
    public Top100MovieResponse getTop100MovieList(){

        return movieService.getTop100MovieListByRating();
    }


}
