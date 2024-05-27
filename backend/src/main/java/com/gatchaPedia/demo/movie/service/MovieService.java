package com.gatchaPedia.demo.movie.service;

import com.gatchaPedia.demo.movie.request.AllMovieGetRequest;
import com.gatchaPedia.demo.movie.request.MovieInfoRequest;
import com.gatchaPedia.demo.movie.request.MovieSearchRequest;
import com.gatchaPedia.demo.movie.response.*;
import jakarta.servlet.http.HttpServletRequest;

public interface MovieService {

    Top100MovieResponse getTop100MovieListByRating();

    MainPageResponse getMoviesForMainPage(HttpServletRequest request);

    MovieRerollResponse movieReroll();

    AllMovieGetResponse getAllMovies(AllMovieGetRequest request);

    MovieInfoResponse getMovieInfo(MovieInfoRequest request);

    MovieSearchResponse search(MovieSearchRequest request);
}
