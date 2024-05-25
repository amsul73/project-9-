package com.gatchaPedia.demo.bookmark.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookmarkCreateOrDeleteResponse {

    private Boolean success;

    private String message;
}
