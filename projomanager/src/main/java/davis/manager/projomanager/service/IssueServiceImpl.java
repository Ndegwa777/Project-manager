package com.wera.wera.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.wera.wera.entities.Issue;
import com.wera.wera.entities.Project;
import com.wera.wera.entities.User;
import com.wera.wera.repositories.IssueRepository;
import com.wera.wera.request.IssueRequest;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class IssueServiceImpl implements IssueService{


    private IssueRepository issueRepository;

    private ProjectService projectService;

    private UserService userService;

    @Override
    public Issue getIssueById(Long issueId) throws Exception {
        Optional<Issue> issue = issueRepository.findById(issueId);
        if(issue.isPresent()){
            return issue.get();
        }
        throw new Exception("No issue found with issueid "+ issueId );
    }

    @Override
    public List<Issue> getIssueByProjectId(Long projectId) throws Exception {
       return issueRepository.findByProjectId(projectId);
    }

    @Override
    public Issue createIssue(IssueRequest issueRequest, User user) throws Exception {
        Project project = projectService.getProjectById(issueRequest.getProjectId());

        Issue issue = new Issue();

        issue.setTitle(issueRequest.getTitle());
        issue.setDescription(issueRequest.getDescription());
        issue.setStatus(issueRequest.getStatus());
        issue.setProjectID(issueRequest.getProjectId());
        issue.setPriority(issueRequest.getPriority());
        issue.setDueDate(issueRequest.getDueDate());

        issue.setProject(project);

        return issueRepository.save(issue);

    }

    @Override
    public void deleteIssue(Long issueId, Long userId) throws Exception {
       getIssueById(issueId);
       issueRepository.deleteById(issueId);
    }

    @Override
    public Issue addUserToIssue(Long issueId, Long userId) throws Exception {

        User user = userService.findUserById(userId);
        Issue issue = getIssueById(issueId);

        issue.setAssignee(user);

       
        return  issueRepository.save(issue);
    }

    @Override
    public Issue updateStatus(Long issueId, String status) throws Exception {
        Issue issue = getIssueById(issueId);

        issue.setStatus(status);

        return issueRepository.save(issue);
    }
    
}
