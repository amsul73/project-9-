package com.gatchaPedia.demo.movie.service;

import com.gatchaPedia.demo.movie.request.AllMovieGetRequest;
import com.gatchaPedia.demo.movie.response.*;

public interface MovieService {

    Top100MovieResponse getTop100MovieListByRating();

    MainPageResponse getMoviesForMainPage();

    MovieRerollResponse movieReroll();

    AllMovieGetResponse getAllMovies(AllMovieGetRequest request);
}
