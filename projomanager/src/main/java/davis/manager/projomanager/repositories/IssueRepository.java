package com.wera.wera.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wera.wera.entities.Issue;

public interface IssueRepository extends JpaRepository<Issue, Long> {
    
    Optional<Issue> findById(Long issueId);

    public List<Issue> findByProjectId(Long projectId);
}
