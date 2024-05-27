package com.gatchaPedia.demo.movie.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MainPageResponse {

    private Boolean success;

    private String gatchaPhotoUrl;

    private Long movieId;

    private List<RecommendMovieInfo> recommendList;
}
