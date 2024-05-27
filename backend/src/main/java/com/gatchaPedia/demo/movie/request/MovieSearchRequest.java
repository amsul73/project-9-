package com.gatchaPedia.demo.movie.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovieSearchRequest {

    private Integer currentPage;

    private String keyWord;
}
