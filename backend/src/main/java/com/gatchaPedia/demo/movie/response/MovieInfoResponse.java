package com.gatchaPedia.demo.movie.response;

import com.gatchaPedia.demo.genre.response.GenreResponse;
import com.gatchaPedia.demo.tag.response.TagResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovieInfoResponse {

    private Boolean success;

    private Long movieId;

    private String title;

    private String overView;

    private String moviePhotoUrl;

    private BigDecimal rating;

    private Boolean bookmarkChecked;

    private List<GenreResponse> genres;

    private List<TagResponse> tags;

    private String message;

}
