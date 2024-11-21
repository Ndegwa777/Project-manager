package com.wera.wera.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wera.wera.DTO.IssueDTO;
import com.wera.wera.entities.Issue;
import com.wera.wera.entities.User;
import com.wera.wera.request.IssueRequest;

import com.wera.wera.response.MessageResponse;
import com.wera.wera.service.IssueService;
import com.wera.wera.service.UserService;


import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/issues")
public class IssueController {
    
    private IssueService issueService;

    private UserService userService;


    @GetMapping("/{issueId}")
    public ResponseEntity<Issue> getIssueById(@PathVariable Long issueId) throws Exception{
        return new ResponseEntity<>(issueService.getIssueById(issueId), HttpStatus.OK);
    } 

    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<Issue>> getIssueByProjectId(@PathVariable Long projectId) throws Exception{

        return new ResponseEntity<>(issueService.getIssueByProjectId(projectId), HttpStatus.OK);
    }


    @PostMapping("")
    public ResponseEntity<IssueDTO> createIssue(@RequestBody IssueRequest issue, 
    @RequestHeader("Authorization") String token) throws Exception{

        User tokenUser = userService.findUserProfileByJwt(token);

            Issue createdIssue = issueService.createIssue(issue, tokenUser);
            IssueDTO issueDTO = new IssueDTO();

            issueDTO.setDescription(createdIssue.getDescription());
            issueDTO.setDueDate(createdIssue.getDueDate());
            issueDTO.setId(createdIssue.getId());
            issueDTO.setPriority(createdIssue.getPriority());
            issueDTO.setProject(createdIssue.getProject());
            issueDTO.setProjectID(createdIssue.getProjectID());
            issueDTO.setStatus(createdIssue.getStatus());
            issueDTO.setTitle(createdIssue.getTitle());
            issueDTO.setTags(createdIssue.getTags());
            issueDTO.setAssignee(createdIssue.getAssignee());

            return new ResponseEntity<>(issueDTO, HttpStatus.OK);
       
    }


    @DeleteMapping("/{issueId}")
    public ResponseEntity<MessageResponse> deleteIssue(@PathVariable Long issueId, 
    @RequestHeader("Authorization") String token ) throws Exception{

        User user = userService.findUserProfileByJwt(token);
        issueService.deleteIssue(issueId, user.getId());

        MessageResponse response = new MessageResponse();
        response.setMessage("Issue deleted");
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{issueId}/assignee/{userId}")
    public ResponseEntity<Issue> addUserToIssue(@PathVariable Long issueId, @PathVariable Long userId) throws Exception{
        Issue issue = issueService.addUserToIssue(issueId, userId);

        return new ResponseEntity<>(issue, HttpStatus.OK);
    }

    @PutMapping("/{issueId}/status/{status}")
    public ResponseEntity<Issue> updateIssueStatus(
        @PathVariable String status,
        @PathVariable Long issueId
    ) throws Exception{

        Issue issue = issueService.updateStatus(issueId, status);

        return new ResponseEntity<>(issue, HttpStatus.OK);
    }
}
