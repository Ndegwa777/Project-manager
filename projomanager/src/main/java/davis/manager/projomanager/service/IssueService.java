package com.wera.wera.service;

import java.util.List;



import com.wera.wera.entities.Issue;
import com.wera.wera.entities.User;
import com.wera.wera.request.IssueRequest;

public interface IssueService {
    

    Issue getIssueById(Long issueId) throws Exception;

    List<Issue> getIssueByProjectId(Long projectId) throws Exception;

    Issue createIssue(IssueRequest issue, User user) throws Exception;


    void deleteIssue(Long issueId, Long userId) throws Exception;


    Issue addUserToIssue(Long issueId, Long userId) throws Exception;

    Issue updateStatus(Long issueId, String status) throws Exception;
}
