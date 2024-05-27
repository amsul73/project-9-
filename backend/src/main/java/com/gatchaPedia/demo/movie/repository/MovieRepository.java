package com.gatchaPedia.demo.movie.repository;

import com.gatchaPedia.demo.movie.entity.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query(value = "SELECT * FROM movie WHERE MATCH(title) AGAINST(:keyword IN NATURAL LANGUAGE MODE)",
            countQuery = "SELECT COUNT(*) FROM movie WHERE MATCH(title) AGAINST(:keyword IN NATURAL LANGUAGE MODE)",
            nativeQuery = true)
    Page<Movie> searchByTitle(@Param("keyword") String keyword, Pageable pageable);
}
