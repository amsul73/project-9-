package com.gatchaPedia.demo.bookmark.controller;

import com.gatchaPedia.demo.bookmark.request.BookmarkCreateOrDeleteRequest;
import com.gatchaPedia.demo.bookmark.response.BookmarkCreateOrDeleteResponse;
import com.gatchaPedia.demo.bookmark.response.BookmarksResponse;
import com.gatchaPedia.demo.bookmark.service.BookmarkService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class BookmarkController {

    private final BookmarkService bookmarkService;


    @PostMapping("/bookmark/{movieId}")
    public BookmarkCreateOrDeleteResponse bookmark(@PathVariable("movieId") Long movieId,
                                                   HttpServletRequest request){

        BookmarkCreateOrDeleteRequest bookmarkRequest = new BookmarkCreateOrDeleteRequest(movieId);

        return bookmarkService.bookmarkCreateOrDelete(bookmarkRequest,request);
    }


    @GetMapping("/bookmarks")
    public BookmarksResponse getBookmarks(HttpServletRequest request){
        return bookmarkService.getBookmarks(request);
    }

}
