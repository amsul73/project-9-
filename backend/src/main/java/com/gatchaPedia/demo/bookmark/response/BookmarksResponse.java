package com.gatchaPedia.demo.bookmark.response;

import com.gatchaPedia.demo.movie.response.BookmarkMovieDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookmarksResponse {

    private Boolean success;

    private List<BookmarkMovieDTO> bookmarkList;

    private String message;
}
