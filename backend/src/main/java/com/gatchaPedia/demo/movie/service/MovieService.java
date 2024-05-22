package com.gatchaPedia.demo.movie.service;

import com.gatchaPedia.demo.movie.response.MainPageResponse;
import com.gatchaPedia.demo.movie.response.MovieRerollResponse;
import com.gatchaPedia.demo.movie.response.Top100MovieListResponse;
import com.gatchaPedia.demo.movie.response.Top100MovieResponse;

public interface MovieService {

    Top100MovieResponse getTop100MovieListByRating();

    MainPageResponse getMoviesForMainPage();

    MovieRerollResponse movieReroll();
}
