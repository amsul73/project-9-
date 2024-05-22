package com.gatchaPedia.demo.movie.service;

import com.gatchaPedia.demo.movie.entity.Movie;
import com.gatchaPedia.demo.movie.repository.MovieRepository;
import com.gatchaPedia.demo.movie.response.MainPageResponse;
import com.gatchaPedia.demo.movie.response.MovieRerollResponse;
import com.gatchaPedia.demo.movie.response.Top100MovieListResponse;
import com.gatchaPedia.demo.movie.response.Top100MovieResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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


    // 모든 사용자에 대해 gatchaPhotoUrl은 최초에는 아무거나 주고 사용자 입력에 대해서 요청을 넘겨줌
    // 로그인 하지 않은 사용자의 경우 , recommendList = null
    // 로그인 된 사용자의 경우, recomendList에 북마크 기반 영화추천 알고리즘 돌려서 해당 영화들 넣어줌

    @Override
    public MainPageResponse getMoviesForMainPage() {

        // 전체 영화중에 50개 가져와서 랜덤으로 그중 하나 보여줌
        Random random = new Random();

        List<Movie> randomMovieList = (List<Movie>) movieRepository.findAll(PageRequest.of(random.nextInt(190),50,Sort.by("id")));
        Movie randomMovie = randomMovieList.get(random.nextInt(randomMovieList.size()));


        // 영화 추천 서비스 , 파이썬 API 서버와 통신후 해당 영화 리스트를 넘김, 아직 영화추천 미구현
        
        return new MainPageResponse(true,randomMovie.getMoviePhotoURL(), null);
    }

    @Override
    public MovieRerollResponse movieReroll() {

        Random random = new Random();

        List<Movie> randomMovieList = (List<Movie>) movieRepository.findAll(PageRequest.of(random.nextInt(190),50,Sort.by("id")));
        Movie randomMovie = randomMovieList.get(random.nextInt(randomMovieList.size()));

        return new MovieRerollResponse(true, randomMovie.getId(), randomMovie.getMoviePhotoURL(), "리롤 성공");
    }


}
