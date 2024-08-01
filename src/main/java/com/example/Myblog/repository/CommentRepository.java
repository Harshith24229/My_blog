package com.example.Myblog.repository;

import com.example.Myblog.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment>findBypostId(Long postId);
}
