package com.gatchaPedia.demo.tag.repository;

import com.gatchaPedia.demo.tag.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRespository extends JpaRepository <Tag, Long> {
    List<Tag> findAllByMovieId(Long movieId);
}
