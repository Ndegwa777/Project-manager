package com.wera.wera.service;

import java.util.List;

import com.wera.wera.entities.Comment;

public interface CommentService {

    Comment createComment(Long issueId, Long userId, String comment) throws Exception;

    void deleteComment(Long commentId, Long userId) throws Exception;

    List<Comment> findCommentByIssueId(Long issueId);
    
}
