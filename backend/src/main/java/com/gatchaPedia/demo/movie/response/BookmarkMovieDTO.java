package com.gatchaPedia.demo.movie.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookmarkMovieDTO {

    private Long movieId;

    private String title;

    private String moviePhotoUrl;
}
