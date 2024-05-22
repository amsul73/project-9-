package com.gatchaPedia.demo.movie.service;

import com.gatchaPedia.demo.bookmark.entity.Bookmark;
import com.gatchaPedia.demo.member.entity.Member;
import com.gatchaPedia.demo.member.exception.MemberAuthException;
import com.gatchaPedia.demo.movie.entity.Movie;
import com.gatchaPedia.demo.movie.repository.MovieRepository;
import com.gatchaPedia.demo.movie.request.AllMovieGetRequest;
import com.gatchaPedia.demo.movie.response.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.*;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService{

    private final static int PAGING_SIZE = 15;

    private final WebClient.Builder webClientBuilder;

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
    public MainPageResponse getMoviesForMainPage(HttpServletRequest request) {

        // 전체 영화중에 50개 가져와서 랜덤으로 그중 하나 보여줌
        Random random = new Random();

        List<Movie> randomMovieList = (List<Movie>) movieRepository.findAll(PageRequest.of(random.nextInt(190),50,Sort.by("id")));
        Movie randomMovie = randomMovieList.get(random.nextInt(randomMovieList.size()));


        // 영화 추천 서비스 , 파이썬 API 서버와 통신후 해당 영화 리스트를 넘김, 아직 영화추천 미구현
        // 원래는 여기서가 아니라 컨트롤러에서 랜덤 영화 service 하나와 영화추천 메소드를 하나 실행했어야 할거같은데 그냥 진행하기로함

        
        // 파이썬 api서버와 통신하기위해서 webflux의 webclient 라는걸 사용해서 비동기식 처리가 가능하다는데 뭔지는 잘 모르겠음
        // Python API 서버와 통신
        String apiUrl = "http://localhost:5000/recommend";

        // 현재 로그인 한 멤버의 북마크 리스트에서 movie의 아이디들만 반환해야함

        HttpSession session = request.getSession(false);
        String sessionId = session.getId();


        if (session == null || session.getAttribute(sessionId) == null) {      // session.get 하면 Member가 나와야함 없으면 에러
            return new MainPageResponse(true,randomMovie.getMoviePhotoURL(),null);
        }

        Member currentMember = (Member) session.getAttribute(sessionId);

        List<Bookmark> bookmarks = currentMember.getBookmarks() ;
        List<Long> movieIds = new ArrayList<>();

        for (Bookmark bookmark : bookmarks){
            movieIds.add(bookmark.getMovie().getId());
        }


        // 요청 데이터 생성
        Map<String, Object> requestData = new HashMap<>();
        requestData.put("movie_ids", movieIds);

        // WebClient 사용하여 비동기 요청 전송, 무슨 코드인지는 잘 모르겠음
        WebClient webClient = webClientBuilder.build();
        RecommendAPIResponse recommendationResponse = webClient.post() // post 요청 날림
                .uri(apiUrl) // 요청을 보낼 URL
                .bodyValue(requestData) // body에 넣을 데이터 setting
                .retrieve() // 요청 보내고 응답 수신 준비중
                .bodyToMono(RecommendAPIResponse.class)  // 응답 받아와서
                .block(); // 블록킹 호출


        List<RecommendMovieInfo> recommendList = recommendationResponse.getMovies();

        return new MainPageResponse(true,randomMovie.getMoviePhotoURL(), recommendList);
    }

    @Override   
    public MovieRerollResponse movieReroll() {

        Random random = new Random();

        List<Movie> randomMovieList = (List<Movie>) movieRepository.findAll(PageRequest.of(random.nextInt(190),50,Sort.by("id")));
        Movie randomMovie = randomMovieList.get(random.nextInt(randomMovieList.size()));

        return new MovieRerollResponse(true, randomMovie.getId(), randomMovie.getMoviePhotoURL(), "리롤 성공");
    }

    @Override
    public AllMovieGetResponse getAllMovies(AllMovieGetRequest request) {

        Page<Movie> movies = movieRepository.findAll(PageRequest.of(request.getCurrentPage(),PAGING_SIZE,Sort.by("Id").ascending()));

        List <MovieResponseForAllMovie> movieList = new ArrayList<>();

        for(Movie movie : movies){

            MovieResponseForAllMovie response = new MovieResponseForAllMovie(movie.getId(), movie.getMoviePhotoURL());
            movieList.add(response);
        }


        return new AllMovieGetResponse(
                true,
                movieList,
                movies.getNumber(),
                movies.getTotalPages(),
                movies.isFirst(),
                movies.isLast(),
                "전체 영화리스트 반환성공"
        );
    }


}
