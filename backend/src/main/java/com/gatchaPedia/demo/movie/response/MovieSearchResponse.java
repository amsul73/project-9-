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
public class MovieSearchResponse {

    private Boolean success;

    private List<MovieResponseForAllMovie> movieList;

    private int currentPage;

    private int totalPage;

    private Boolean isFirst;

    private Boolean isLast;

    private String message;

}
