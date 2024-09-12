package com.example.Blog.Repository;

import com.example.Blog.Models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
