package com.codeup.gonzaloblog.repositories;

import com.codeup.gonzaloblog.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
