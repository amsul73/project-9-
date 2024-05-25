package com.gatchaPedia.demo.movie.entity;

import com.gatchaPedia.demo.genre.entity.Genre;
import com.gatchaPedia.demo.tag.entity.Tag;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "movie_id")
    private Long id;

    private String title;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String overView;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String moviePhotoURL;

    private BigDecimal rating;


    @OneToMany(mappedBy = "movie")
    private List<Genre> genres;

    @OneToMany (mappedBy = "movie")
    private List<Tag> tags;
}
