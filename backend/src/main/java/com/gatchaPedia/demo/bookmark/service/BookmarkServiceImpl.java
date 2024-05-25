package com.gatchaPedia.demo.bookmark.service;

import com.gatchaPedia.demo.bookmark.entity.Bookmark;
import com.gatchaPedia.demo.bookmark.repository.BookmarkRepository;
import com.gatchaPedia.demo.bookmark.request.BookmarkCreateOrDeleteRequest;
import com.gatchaPedia.demo.bookmark.response.BookmarkCreateOrDeleteResponse;
import com.gatchaPedia.demo.bookmark.response.BookmarksResponse;
import com.gatchaPedia.demo.member.entity.Member;
import com.gatchaPedia.demo.movie.entity.Movie;
import com.gatchaPedia.demo.movie.repository.MovieRepository;
import com.gatchaPedia.demo.movie.response.BookmarkMovieDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookmarkServiceImpl implements BookmarkService{

    private final BookmarkRepository bookmarkRepository;

    private final MovieRepository movieRepository;


    @Transactional
    @Override
    public BookmarkCreateOrDeleteResponse bookmarkCreateOrDelete(BookmarkCreateOrDeleteRequest bookmarkRequest, HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        String sessionId = session.getId();
        Member loginMember = (Member) session.getAttribute(sessionId);

        if(bookmarkRepository.existsByMemberIdAndMovieId(loginMember.getId(),bookmarkRequest.getMovieId())){

            bookmarkRepository.deleteByMemberIdAndMovieId(loginMember.getId(),bookmarkRequest.getMovieId());
            
            return new BookmarkCreateOrDeleteResponse(false, "북마크 삭제");
        }
        else {

            Bookmark bookmark = Bookmark.builder()
                    .movie(movieRepository.findById(bookmarkRequest.getMovieId()).get())
                    .member(loginMember)
                    .build();

            bookmarkRepository.save(bookmark);


            return new BookmarkCreateOrDeleteResponse(true, "북마크 생성");
        }
    }

    @Override
    public BookmarksResponse getBookmarks(HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        String sessionId = session.getId();
        Member loginMember = (Member) session.getAttribute(sessionId);

        List<Bookmark> bookmarks = bookmarkRepository.findAllByMemberId(loginMember.getId());



        List<Long> movieIds = bookmarks.stream()
                .map(bookmark -> bookmark.getMovie().getId())
                .toList();

        List<Movie> movies = movieRepository.findAllById(movieIds);

        List<BookmarkMovieDTO> movieDTOs = movies.stream()
                .map(movie -> new BookmarkMovieDTO(
                        movie.getId(),
                        movie.getTitle(),
                        movie.getMoviePhotoURL()
                ))
                .toList();


        return new BookmarksResponse(true, movieDTOs, "내 북마크 리스트 반환");
    }
}
