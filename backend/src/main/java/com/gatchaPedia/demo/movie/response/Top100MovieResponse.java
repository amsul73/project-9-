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
public class Top100MovieResponse {

    private Boolean success;

    private List<Top100MovieListResponse> Top100MovieList;

    private String message;
}
