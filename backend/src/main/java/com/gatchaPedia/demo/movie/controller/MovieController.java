package com.gatchaPedia.demo.movie.controller;


import com.gatchaPedia.demo.movie.request.AllMovieGetRequest;
import com.gatchaPedia.demo.movie.request.MovieInfoRequest;
import com.gatchaPedia.demo.movie.request.MovieSearchRequest;
import com.gatchaPedia.demo.movie.response.*;
import com.gatchaPedia.demo.movie.service.MovieService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
    public MovieInfoResponse movieInfo(@PathVariable("movieId") Long movieId,
                                       HttpServletRequest httpServletRequest){

        MovieInfoRequest request = new MovieInfoRequest(movieId);

        return movieService.getMovieInfo(request,httpServletRequest);
    }


    @PostMapping("/movies/search/{currentPage}")
    public MovieSearchResponse movieSearch(@PathVariable("currentPage") int currentPage,
                                           @RequestBody MovieSearchRequest request){


        request.setCurrentPage(currentPage);

        return movieService.search(request);
    }

}
