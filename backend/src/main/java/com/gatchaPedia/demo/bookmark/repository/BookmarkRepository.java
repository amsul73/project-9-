package com.gatchaPedia.demo.bookmark.repository;

import com.gatchaPedia.demo.bookmark.entity.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {


    boolean existsByMemberIdAndMovieId(Long id, Long movieId);

    void deleteByMemberIdAndMovieId(Long id, Long movieId);

    List<Bookmark> findAllByMemberId(Long id);
}