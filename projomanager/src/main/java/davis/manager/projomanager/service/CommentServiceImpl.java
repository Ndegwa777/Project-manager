package com.wera.wera.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.wera.wera.entities.Comment;
import com.wera.wera.entities.Issue;
import com.wera.wera.entities.User;
import com.wera.wera.repositories.CommentRepository;
import com.wera.wera.repositories.IssueRepository;
import com.wera.wera.repositories.UserRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CommentServiceImpl implements CommentService{

    private CommentRepository commentRepository;

    private IssueRepository issueRepository;

    private UserRepository userRepository;

    @Override
    public Comment createComment(Long issueId, Long userId, String content) throws Exception {
        
        Optional<Issue> optionalIssue = issueRepository.findById(issueId);
        Optional<User> optionalUser = userRepository.findById(userId);

        if(optionalIssue.isEmpty()){
            throw new Exception("Issue not found with Id "+ issueId);
        }

        if(optionalUser.isEmpty()){
            throw new Exception("User not found with Id "+ userId);
        }

        Issue issue = optionalIssue.get();
        User user = optionalUser.get();

        Comment comment = new Comment();

        comment.setIssue(issue);
        comment.setUser(user);
        comment.setCreatedDateTime(LocalDateTime.now());
        comment.setContent(content);

        Comment savedComment = commentRepository.save(comment);

        return savedComment;


    }

    @Override
    public void deleteComment(Long commentId, Long userId) throws Exception {
       Optional<Comment> optionalComment = commentRepository.findById(commentId);
       Optional<User> optionalUser = userRepository.findById(userId);

       if(optionalComment.isEmpty()){
        throw new Exception("Comment not found with id "+ commentId);
       }

       if(optionalUser.isEmpty()){
        throw new Exception("User not found with id "+ userId);
       }

       Comment comment = optionalComment.get();
       User user = optionalUser.get();

       if(comment.getUser().equals(user)){
        commentRepository.delete(comment);
       }else{
        throw new Exception ("User does not have permission to delete this comment");
       }
    }

    @Override
    public List<Comment> findCommentByIssueId(Long issueId) {
        return commentRepository.findByIssueId(issueId);
    }
    
}
