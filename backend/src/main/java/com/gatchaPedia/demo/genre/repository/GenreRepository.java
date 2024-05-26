package com.gatchaPedia.demo.genre.repository;

import com.gatchaPedia.demo.genre.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GenreRepository extends JpaRepository<Genre, Long> {
    List<Genre> findAllByMovieId(Long movieId);
}
