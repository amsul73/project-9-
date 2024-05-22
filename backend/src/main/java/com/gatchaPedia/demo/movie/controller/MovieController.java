package com.gatchaPedia.demo.movie.controller;


import com.gatchaPedia.demo.movie.request.AllMovieGetRequest;
import com.gatchaPedia.demo.movie.response.AllMovieGetResponse;
import com.gatchaPedia.demo.movie.response.MainPageResponse;
import com.gatchaPedia.demo.movie.response.MovieRerollResponse;
import com.gatchaPedia.demo.movie.response.Top100MovieResponse;
import com.gatchaPedia.demo.movie.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/movies/{currentPage}")
    public AllMovieGetResponse AllMoviesGet(@PathVariable("currentPage") int currentPage){

        AllMovieGetRequest request = new AllMovieGetRequest(currentPage);


        return movieService.getAllMovies(request);
    }

}
