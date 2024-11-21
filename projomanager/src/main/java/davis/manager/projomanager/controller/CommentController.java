package com.wera.wera.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wera.wera.entities.Comment;
import com.wera.wera.entities.User;
import com.wera.wera.request.CreateCommentRequest;
import com.wera.wera.response.MessageResponse;
import com.wera.wera.service.CommentService;
import com.wera.wera.service.UserService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private CommentService commentService;

    private UserService userService;

    @PostMapping("")
    public ResponseEntity<Comment> createComment(@RequestBody CreateCommentRequest req,
    @RequestHeader("Authorization")String jwt) throws Exception {

        User user = userService.findUserProfileByJwt(jwt);
        Comment createdComment = commentService.createComment(req.getIssueId(), user.getId(), req.getContent());

        return new ResponseEntity<>(createdComment, HttpStatus.CREATED);

    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<MessageResponse> deleteComment(@PathVariable Long commentId,
    @RequestHeader("Authorization")String jwt) throws Exception{
        User user = userService.findUserProfileByJwt(jwt);
        commentService.deleteComment(commentId, user.getId());
        MessageResponse response = new MessageResponse();
        response.setMessage("Comment deleted successfully");

        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);

    }

    @GetMapping("/{issueId}")
    public ResponseEntity<List<Comment>> getCommentsByIssueId(@PathVariable Long issueId) throws Exception{
        List<Comment> comments = commentService.findCommentByIssueId(issueId);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }
    
}
