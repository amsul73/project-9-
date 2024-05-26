package com.gatchaPedia.demo.movie.controller;


import com.gatchaPedia.demo.movie.request.AllMovieGetRequest;
import com.gatchaPedia.demo.movie.request.MovieInfoRequest;
import com.gatchaPedia.demo.movie.response.*;
import com.gatchaPedia.demo.movie.service.MovieService;
import jakarta.servlet.http.HttpServletRequest;
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



    @GetMapping("/mainpage")
    public MainPageResponse mainPage(HttpServletRequest request){

        return movieService.getMoviesForMainPage(request);
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


    @GetMapping("/movie/{movieId}")
    public MovieInfoResponse movieInfo(@PathVariable("movieId") Long movieId){

        MovieInfoRequest request = new MovieInfoRequest(movieId);

        return movieService.getMovieInfo(request);
    }

}
