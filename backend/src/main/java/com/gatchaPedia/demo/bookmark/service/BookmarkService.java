package com.gatchaPedia.demo.bookmark.service;

import com.gatchaPedia.demo.bookmark.request.BookmarkCreateOrDeleteRequest;
import com.gatchaPedia.demo.bookmark.response.BookmarkCreateOrDeleteResponse;
import com.gatchaPedia.demo.bookmark.response.BookmarksResponse;
import jakarta.servlet.http.HttpServletRequest;

public interface BookmarkService {
    BookmarkCreateOrDeleteResponse bookmarkCreateOrDelete(BookmarkCreateOrDeleteRequest bookmarkRequest, HttpServletRequest request);

    BookmarksResponse getBookmarks(HttpServletRequest request);
}
