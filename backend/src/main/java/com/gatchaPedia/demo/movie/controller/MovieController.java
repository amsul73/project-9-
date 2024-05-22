package com.gatchaPedia.demo.movie.controller;


import com.gatchaPedia.demo.movie.response.MainPageResponse;
import com.gatchaPedia.demo.movie.response.MovieRerollResponse;
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



    @GetMapping("mainpage")
    public MainPageResponse mainPage(){

        return movieService.getMoviesForMainPage();
    }


    @GetMapping("/reroll")
    public MovieRerollResponse MovieReroll(){

        return movieService.movieReroll();
    }


}
