package com.gatchaPedia.demo.movie.service;

import com.gatchaPedia.demo.movie.entity.Movie;
import com.gatchaPedia.demo.movie.repository.MovieRepository;
import com.gatchaPedia.demo.movie.response.Top100MovieListResponse;
import com.gatchaPedia.demo.movie.response.Top100MovieResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService{

    private final MovieRepository movieRepository;


    @Override
    public Top100MovieResponse getTop100MovieListByRating(){

        // 페이징 없이 영화 100개의 List를 넘기기로 함
        // 그냥 current page =0, pagingsize=100, sort = 별점순 으로 spring data jpa 이용

        List<Movie> movieList = movieRepository.findAll(PageRequest.of(0,100,Sort.by(Sort.Direction.DESC,"rating"))).getContent();
        List<Top100MovieListResponse> movies = new ArrayList<>();

        for(Movie movie : movieList){

             Top100MovieListResponse response = new Top100MovieListResponse(movie.getId(),movie.getMoviePhotoURL());
             movies.add(response);
        }

        return new Top100MovieResponse(true,movies,"별점순 TOP 100 영화리스트 반환 성공");
    }
}
