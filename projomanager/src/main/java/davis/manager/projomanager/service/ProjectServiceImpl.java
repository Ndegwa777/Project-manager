package com.wera.wera.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.wera.wera.entities.Chat;
import com.wera.wera.entities.Project;
import com.wera.wera.entities.User;
import com.wera.wera.repositories.ProjectRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ProjectServiceImpl implements ProjectService{

    private ProjectRepository projectRepository;

    private UserService userService;

    private ChatService chatService;

    @Override
    public Project createProject(Project project, User user) throws Exception {
        Project newProject = new Project();
        newProject.setOwner(user);
        newProject.setTags(project.getTags());
        newProject.setName(project.getName());
        newProject.setCategory(project.getCategory());
        newProject.setDescription(project.getDescription());
        newProject.getTeam().add(user);
        
       Project savedProject= projectRepository.save(newProject);

       Chat chat = new Chat();
       chat.setProject(savedProject);

       Chat projectChat = chatService.createChat(chat);

       savedProject.setChat(projectChat);

       return savedProject;
    }

    @Override
    public List<Project> getProjectByTeam(User user, String category, String tag) throws Exception {
        List<Project> projects = projectRepository.findByTeamContainingOrOwner(user, user);

        if(category != null){
            projects = projects.stream().filter(project -> project.getCategory().equals(category))
                                        .collect(Collectors.toList());
        }


        if(tag != null){
            projects = projects.stream().filter(project -> project.getTags().contains(tag))
                                        .collect(Collectors.toList());
        }

        return projects;
    }

    @Override
    public Project getProjectById(Long projectId) throws Exception {
        Optional<Project> project = projectRepository.findById(projectId);

        if(project.isEmpty()){
            throw new Exception("Project not found");
        }

        return project.get();
    }

    @Override
    public void deleteProject(Long projectId, Long userId) throws Exception {
        
        getProjectById(projectId);
        userService.findUserById(userId);

        projectRepository.deleteById(projectId);
    }

    @Override
    public Project updateProject(Project updatedProject, Long id) throws Exception {
       Project project = getProjectById(id);

        project.setTags(updatedProject.getTags());
        project.setName(updatedProject.getName());
        project.setDescription(updatedProject.getDescription());

        return projectRepository.save(project);
    }

    @Override
    public void addUserToProject(Long projectId, Long userId) throws Exception {
        Project project = getProjectById(projectId);
        User user = userService.findUserById(userId);

       for(User member: project.getTeam()){
        if(member.getId().equals(userId)){
            return;
        }
       }
       project.getChat().getUsers().add(user);
       project.getTeam().add(user);
       projectRepository.save(project);
    }

    @Override
    public void removeUserFromProject(Long projectId, Long userId) throws Exception {
        Project project = getProjectById(projectId);
        User user = userService.findUserById(userId);

        if(project.getTeam().contains(user)){
            project.getChat().getUsers().remove(user);
            project.getTeam().remove(user);
        }
        projectRepository.save(project);
    }

    @Override
    public Chat getChatByProjectId(Long projectId) throws Exception {
        Project project = getProjectById(projectId);

        return project.getChat();
    }

    @Override
    public List<Project> searchProjects(String keyword, User user) throws Exception {
        

        List<Project> projects = projectRepository.findByNameContainingAndTeamContains(keyword, user);
        return projects;
    }
    

}
