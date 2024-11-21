package com.wera.wera.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wera.wera.entities.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    
    List<Comment> findByIssueId(Long issueId);


}
