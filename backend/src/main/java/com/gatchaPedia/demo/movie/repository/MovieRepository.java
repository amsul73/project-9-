package com.gatchaPedia.demo.movie.repository;

import com.gatchaPedia.demo.movie.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {

}
