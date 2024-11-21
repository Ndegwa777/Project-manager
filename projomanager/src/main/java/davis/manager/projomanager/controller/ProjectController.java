package com.wera.wera.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wera.wera.entities.Chat;
import com.wera.wera.entities.Invitation;
import com.wera.wera.entities.Project;
import com.wera.wera.entities.User;
import com.wera.wera.request.InvitationRequest;
import com.wera.wera.response.MessageResponse;
import com.wera.wera.service.InvitationService;
import com.wera.wera.service.ProjectService;
import com.wera.wera.service.UserService;

import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@AllArgsConstructor
@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private ProjectService projectService;

    private UserService userService;

    private InvitationService invitationService;

    @GetMapping("")    
    public ResponseEntity <List<Project>> getProjects(
        @RequestParam(required = false)String category,
        @RequestParam(required= false)String tag,
        @RequestHeader("Authorization")String jwt
        ) throws Exception{
            User user = userService.findUserProfileByJwt(jwt);
            List<Project> projects = projectService.getProjectByTeam(user, category, tag);

            return new ResponseEntity<>(projects, HttpStatus.OK);

    }

    @GetMapping("/{projectId}")    
    public ResponseEntity <Project> getProjectById(
        @PathVariable Long projectId,
        @RequestHeader("Authorization")String jwt
        ) throws Exception{
            Project project = projectService.getProjectById(projectId);

            return new ResponseEntity<>(project, HttpStatus.OK);

    }

    @PostMapping("")
    public ResponseEntity <Project> createProject(
        @RequestHeader("Authorization")String jwt,
        @RequestBody Project project
        ) throws Exception{
            User user = userService.findUserProfileByJwt(jwt);
            Project createdProject = projectService.createProject(project, user);

            return new ResponseEntity<>(createdProject, HttpStatus.CREATED);

    }


    @PatchMapping("/{projectId}")
    public ResponseEntity <Project> updateProject(
        @PathVariable Long projectId,
        @RequestHeader("Authorization")String jwt,
        @RequestBody Project project
        ) throws Exception{
            Project updatedProject = projectService.updateProject(project, projectId);

            return new ResponseEntity<>(updatedProject, HttpStatus.OK);

    }


    @DeleteMapping("/{projectId}")
    public ResponseEntity <MessageResponse> deleteProject(
        @PathVariable Long projectId,
        @RequestHeader("Authorization")String jwt
        ) throws Exception{
            User user = userService.findUserProfileByJwt(jwt);
            projectService.deleteProject(projectId, user.getId());;

            MessageResponse res = new MessageResponse("Project deleted successfully");
            return new ResponseEntity<>(res, HttpStatus.NO_CONTENT);

    }

    @GetMapping("/search")    
    public ResponseEntity <List<Project>> searchProjects(
        @RequestParam(required = false)String keyword,
        @RequestHeader("Authorization")String jwt
        ) throws Exception{
            User user = userService.findUserProfileByJwt(jwt);
            List<Project> projects = projectService.searchProjects(keyword, user);

            return new ResponseEntity<>(projects, HttpStatus.OK);

    }


    @GetMapping("/{projectId}/chat")    
    public ResponseEntity <Chat> getChatByProjectId(
        @PathVariable Long projectId,
        @RequestHeader("Authorization")String jwt
        ) throws Exception{

            Chat chat = projectService.getChatByProjectId(projectId);

            return new ResponseEntity<>(chat, HttpStatus.OK);

    }

    @PostMapping("/invite")
    public ResponseEntity <MessageResponse> inviteProject(
        @RequestBody InvitationRequest request,
        @RequestHeader("Authorization") String jwt
        
        ) throws Exception{
            // User user = userService.findUserProfileByJwt(jwt);
            invitationService.sendInvitation(request.getEmail(),request.getProjectId());

            MessageResponse response = new MessageResponse("User invitation sent");

            return new ResponseEntity<>(response, HttpStatus.OK);

    }


    @GetMapping("/accept_invitation")
    public ResponseEntity <Invitation> acceptInvitationToProject(
        @RequestHeader("Authorization")String jwt,
        @RequestParam String token
        ) throws Exception{
            User user = userService.findUserProfileByJwt(jwt);
         Invitation invitation = invitationService.accepInvitation(token, user.getId());

            projectService.addUserToProject(invitation.getProjectId(), user.getId());

            return new ResponseEntity<>(invitation, HttpStatus.ACCEPTED);

    }






    
    
}
