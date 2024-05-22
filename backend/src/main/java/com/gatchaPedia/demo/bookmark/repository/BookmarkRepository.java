package com.gatchaPedia.demo.bookmark.repository;

import com.gatchaPedia.demo.bookmark.entity.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
}
