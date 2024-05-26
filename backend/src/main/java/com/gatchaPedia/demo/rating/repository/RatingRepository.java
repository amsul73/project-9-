package com.gatchaPedia.demo.rating.repository;

import com.gatchaPedia.demo.rating.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Long> {
    List<Rating> findAllByMovieId(Long movieId);
}
